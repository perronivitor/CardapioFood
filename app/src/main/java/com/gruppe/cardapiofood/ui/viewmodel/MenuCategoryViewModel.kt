package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.listener.GetMenuCategoryList
import com.gruppe.cardapiofood.ui.model.Categories
import com.gruppe.cardapiofood.ui.repository.MenuCategoryRepository

class MenuCategoryViewModel : ViewModel(){

    private val repository = MenuCategoryRepository()

    var mCategoryItemList = MutableLiveData<MutableList<Categories?>>()

    fun getMenuCategoryList(){
        repository.getMenuCatedoryList(object : GetMenuCategoryList{
            override fun onSuccess(categoryItens: MutableList<Categories?>) {
                mCategoryItemList.postValue(categoryItens)
            }

            override fun onErrorCode(errorCode: Int, message: String) {
                TODO("Not yet implemented")
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }
}