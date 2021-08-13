package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.ui.repository.MenuCategoryRepository

class MenuCategoryViewModel : ViewModel(){

    private val _mCategoryItemList = MutableLiveData<List<CategoryData>>()
    val mCategoryItemList = Transformations.map(_mCategoryItemList){
        it.mapTo(arrayListOf()){ c ->
            Category(
                id = c.idCategory,
                title = c.strCategory,
                imgUrl = c.img
            )
        }
    }

    fun getMenuCategoryList(){
        MenuCategoryRepository.getMenuCatedoryList(
            { categories ->
                _mCategoryItemList.postValue(categories)
            },{ throwable ->
                // TODO
            }
        )
    }
}