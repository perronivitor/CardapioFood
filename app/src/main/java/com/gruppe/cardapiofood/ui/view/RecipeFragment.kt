package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.gruppe.cardapiofood.*
import com.gruppe.cardapiofood.databinding.FragmentRecipeBinding
import com.gruppe.cardapiofood.ui.adapter.RecipeAdapter
import com.gruppe.cardapiofood.ui.viewmodel.RecipeViewModel

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private val args: RecipeFragmentArgs by navArgs()
    private lateinit var recipeTitle: String

    private lateinit var vm: RecipeViewModel

    //Verifica se existe uma instancia
    private var isIntance = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            (requireActivity() as MainActivity),
            RecipeViewModel.RecipeViewModelFactory(requireActivity().application))
            .get(RecipeViewModel::class.java)

        recipeTitle = args.recipeTitle

        isIntance = savedInstanceState != null
        if (!isIntance) {
            //Verifico se existe registro no ROOM
            vm.verifyIfMealContainInFavoriteDb(recipeTitle) { exist ->
                if (exist) {
                    Log.i("observerRecipe", "ROOM")
                    vm.getRecipe(recipeTitle) { recipe ->
                        Log.i("observerRecipe", recipe.toString())
                        vm.setRecipe(recipe)
                    }
                } else {
                    Log.i("observerRecipe", "API")
                    vm.getIngredients(recipeTitle)
                }
            }

        }

        observer()
        listeners()
    }

    private fun listeners() {
        binding.btFavorite.setOnClickListener {
            vm.setFavorite(binding.btFavorite.isChecked)
        }
    }

    private fun observer() {
        vm.mRecipe.nonNullObserve(viewLifecycleOwner, { recipe ->
            Log.i("observerRecipe", recipe.toString())
            binding.listView.apply {
                adapter = RecipeAdapter(requireActivity(), recipe.ingredients)
            }
            binding.imgMeal.load(recipe.imgUrl)
            binding.tvPrepareMode.text = recipe.prepareMode
            binding.btFavorite.isChecked = recipe.isFavorite
            binding.tvMeal.text = recipe.title
        })

        vm.error.nonNullObserve(viewLifecycleOwner, {
            showDialogError(requireContext(), "Error", it.toString())
            vm.error.postValue(null)
        })

        vm.mProgressBar.nonNullObserve(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
            vm.mProgressBar.postValue(null)
        }

        vm.isFavorite.nonNullObserve(viewLifecycleOwner) { isCheck ->
            if (isCheck) save() else delete()
        }

    }

    private fun save() {
        vm.saveFavoriteMeal()
    }

    private fun delete() {
        vm.deleteFavoriteMeal()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}