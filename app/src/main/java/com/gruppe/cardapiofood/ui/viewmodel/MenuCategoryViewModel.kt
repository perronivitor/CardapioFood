package com.gruppe.cardapiofood.ui.viewmodel


import androidx.lifecycle.*
import com.gruppe.cardapiofood.data.repository.MenuCategoryRepository

class MenuCategoryViewModel : ViewModel() {
    fun getMenuCategoryList() = MenuCategoryRepository.getMenuCatedoryList()

}