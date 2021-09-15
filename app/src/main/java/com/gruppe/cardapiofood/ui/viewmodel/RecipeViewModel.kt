package com.gruppe.cardapiofood.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gruppe.cardapiofood.data.repository.RecipeRepository
import com.gruppe.cardapiofood.data.room.*
import com.gruppe.cardapiofood.retrofit.Resultado
import com.gruppe.cardapiofood.ui.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel(private val repository: FavoriteRepository) : ViewModel() {

    private var _mRecipe = MutableLiveData<Recipe>()
    val mRecipe get() = _mRecipe

    private var _mIngredients = MutableLiveData<List<IngredientsEntity>>()
    val mIngredient = Transformations.map(_mIngredients) {
        it.mapTo(arrayListOf()) { ing ->
            ing.ingredientName
        }
    }

    private var _isFavorite = MutableLiveData(false)
    val isFavorite get() = _isFavorite

    var error = MutableLiveData<String?>(null)

    var mProgressBar = MutableLiveData<Boolean>(null)

    //Room
    fun getIngredients(meal: String) {
        launchDataLoad {
            val repo = RecipeRepository.getIngredients(meal)
            when (repo) {
                is Resultado.Sucesso -> {
                    repo.dado?.let {
                        _mRecipe.postValue(it)
                    }
                }
                is Resultado.Erro -> {
                    //Tratrar Erros
                }
            }
        }
    }

    fun updateReciper() {
        _mRecipe.value?.let { recipe ->
            recipe.isFavorite = _isFavorite.value!!
            _mRecipe.postValue(recipe)
        }

    }

    fun setFavorite(isChecked: Boolean) {
        _isFavorite.postValue(isChecked)
        updateReciper()
    }

    fun saveFavoriteMeal() {
        launchDataLoad {

            _mRecipe.value?.let { r ->
                val repo = RecipeWithIngredients(recipe = RecipeEntity(
                    title = r.title,
                    imgUrl = r.imgUrl,
                    prepareMode = r.prepareMode,
                    isFavorite = _isFavorite.value!!
                ),
                ingredient = r.ingredients
                    )
            }

            repository.save()
        }
    }

    fun deleteFavoriteMeal() {
        if (_mRecipe.value != null) {
            launchDataLoad {
                repository.delete(_mRecipe.value!!)
            }
        }
    }

    fun setRecipe(recipe: List<RecipeWithIngredients>) {
        recipe[0]?.let { rec ->
            _mRecipe.postValue(rec.recipe)
            _mIngredients.postValue(rec.ingredient)
        }


    }

    fun getRecipe(title: String, recipe: (recipe: List<RecipeWithIngredients>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.i("observerRecipe", "ViewModel Repositor")
                recipe(repository.getRecipe(title))
                Log.i("observerRecipe", "Retorno repository -> ${repository.getRecipe(title)}")
            } catch (e: Exception) {
                Log.i("observerRecipe", "Exception -> ${e.message}")
            }
        }
    }

    fun verifyIfMealContainInFavoriteDb(title: String, exist: (isExist: Boolean) -> Unit) {
        launchDataLoad {
            val repo = repository.searchMealInFavoriteList(title)
            exist(repo > 0)
        }
    }

    fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                mProgressBar.postValue(true)
                block()
            } catch (e: Exception) {
                //Como tratar Exception?
            } finally {
                mProgressBar.postValue(false)
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
                return return RecipeViewModel(
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