package com.gruppe.cardapiofood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.data.repository.MenuCategoryRepository
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.HttpRetryException
import java.util.concurrent.TimeoutException

class MenuCategoryViewModel : ViewModel() {

    var mProgressBar = MutableLiveData<Boolean>(null)

    var error = MutableLiveData<String>()

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
        viewModelScope.launch {
            try {
                mProgressBar.postValue(true)
                val cat = MenuCategoryRepository.getMenuCatedoryList()
                _mCategoryItemList.postValue(cat)
            } catch (e: ConnectException) {
                Log.e("Error", e.message.toString())
                error.postValue("Falha na comunicação com API")
            }catch (e: Exception) {
                Log.e("Error", e.message.toString())
                error.postValue("Ocorreu uma falha desconhecida")
            } finally {
                mProgressBar.postValue(false)
            }
        }
    }
}