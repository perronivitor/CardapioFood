package com.gruppe.cardapiofood.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gruppe.cardapiofood.data.repository.RecipeRepository
import com.gruppe.cardapiofood.data.room.FavoriteDAO
import com.gruppe.cardapiofood.data.room.FavoriteMealDataBase
import com.gruppe.cardapiofood.data.room.FavoriteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class RecipeViewModel(private val repository: FavoriteRepository) : ViewModel() {

    private var _mMealIngredientList = MutableLiveData<Recipe>()
    val mMealIngredientItemList get() = _mMealIngredientList

    private var _isFavorite = MutableLiveData(false)
    val isFavorite get() = _isFavorite

    var error = MutableLiveData<String?>(null)

    var _mProgressBar = MutableLiveData<Boolean>(null)

    fun getIngredients(meal: String) {
        launchDataLoad {
            RecipeRepository.getIngredients(meal,
                { recipe -> _mMealIngredientList.postValue(recipe) },
                { throwable -> error.postValue(throwable.toString()) })
        }
    }

    fun setFavorite(){
        _isFavorite.postValue(!_isFavorite.value!!)
    }

    fun setIsCheckIngredient(position: Int) {
        try {
            val recipe = mMealIngredientItemList.value
            recipe!!.ingredients[position].isCheck = !recipe.ingredients[position].isCheck
            _mMealIngredientList.postValue(recipe!!)
        } catch (e: Exception) {
            Log.e("RecipeViewModel", e.message.toString())
        }
    }

    fun saveFavoriteMeal(meal : Meal){
        launchDataLoad {
            repository.save(meal)
        }
    }

    fun deleteFavoriteMeal(meal: Meal){
        launchDataLoad {
            repository.delete(meal)
        }
    }

    fun verifyIfMealContainInFavoriteDb(title:String){
        launchDataLoad {
          _isFavorite.postValue(repository.searchMealInFavoriteList(title) >=1)
        }
    }

    fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _mProgressBar.postValue(true)
                block()
            } catch (e: Exception) {
                //Como tratar Exception?
            } finally {
                _mProgressBar.postValue(false)
            }
        }
    }

    /**
     * FACTORY
     */
    class RecipeViewModelFactory constructor(private val application: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
                return  return RecipeViewModel(
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