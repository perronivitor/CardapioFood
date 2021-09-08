package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.*
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.data.repository.MenuCategoryRepository
import kotlinx.coroutines.launch

class MenuCategoryViewModel : ViewModel() {

    var mProgressBar = MutableLiveData<Boolean>(null)

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
                mProgressBar.postValue(true)
                block()
            }catch (e: Exception){
                //Como tratar Exception?
            }finally {
                mProgressBar.postValue(false)
            }
        }
    }

}