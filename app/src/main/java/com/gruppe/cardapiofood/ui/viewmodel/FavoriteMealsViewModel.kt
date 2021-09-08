package com.gruppe.cardapiofood.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gruppe.cardapiofood.data.room.FavoriteMealDataBase
import com.gruppe.cardapiofood.data.room.FavoriteRepository


class FavoriteMealsViewModel(private val repository: FavoriteRepository) : ViewModel() {

    private var _mFavoriteMelsList = repository.favoriteList
    val mFavoriteMealsList get() = _mFavoriteMelsList

    var mProgressBar = MutableLiveData<Boolean>(false)

    /**
     * FACTORY
     */
    class FavoriteMealsViewModelFactory constructor(private val application: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteMealsViewModel::class.java)) {
                return  return FavoriteMealsViewModel(
                    repository = FavoriteRepository(
                        favoriteDAO = FavoriteMealDataBase
                            .getInstance(application.applicationContext).favoriteDAO
                    )
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }


}