package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.ui.repository.MenuCategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MenuCategoryViewModel : ViewModel() {

    var _mProgressBar = MutableLiveData<Boolean>(null)

    private val _mCategoryItemList = MutableLiveData<List<CategoryData>>()
    val mCategoryItemList = Transformations.map(_mCategoryItemList) {
        it.mapTo(arrayListOf()) { c ->
            Category(
                id = c.idCategory,
                title = c.strCategory,
                imgUrl = c.imgUrl
            )
        }
    }

    fun getMenuCategoryList() {
        launchDataLoad {
            MenuCategoryRepository.getMenuCatedoryList(
                { categories ->
                    _mCategoryItemList.postValue(categories)
                }, { throwable ->
                    // TODO
                }
            )
        }
    }

    fun launchDataLoad(block : suspend () -> Unit ){
        viewModelScope.launch {
            try{
                _mProgressBar.postValue(true)
                block()
            }catch (e: Exception){
                //Como tratar Exception?
            }finally {
                _mProgressBar.postValue(false)
            }
        }
    }

}