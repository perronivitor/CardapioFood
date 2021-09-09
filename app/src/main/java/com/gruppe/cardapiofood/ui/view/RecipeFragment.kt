package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.*
import com.gruppe.cardapiofood.databinding.FragmentRecipeBinding
import com.gruppe.cardapiofood.ui.adapter.RecipeAdapter
import com.gruppe.cardapiofood.ui.viewmodel.RecipeViewModel
import com.gruppe.cardapiofood.ui.viewmodel.Meal

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private val args: RecipeFragmentArgs by navArgs()
    private lateinit var meal: Meal

    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            (requireActivity() as MainActivity),
            RecipeViewModel.RecipeViewModelFactory(requireActivity().application))
            .get(RecipeViewModel::class.java)

        meal = args.meal
        viewModel.getIngredients(meal.title)

        //Inserir bloco para verificar se existe no BD aquela receita salva
        viewModel.verifyIfMealContainInFavoriteDb(meal.title)

        binding.imgMeal.load(meal.imgUrl)
        binding.tvMeal.text = meal.title

        observer()

        //Listeners do Botão Favorito
        binding.btFavorite.setOnClickListener {
            viewModel.setFavorite(binding.btFavorite.isChecked)
        }
    }

    private fun observer() {
        viewModel.mMealIngredientItemList.nonNullObserve(viewLifecycleOwner, {listIngredients->
            binding.listView.apply {
                adapter = RecipeAdapter(requireActivity(),listIngredients.ingredients){
                    setIsCheckIngredient(it)
                }
            }
        })

        viewModel.error.nonNullObserve(viewLifecycleOwner,{
            showDialogError(requireContext(),"Error",it.toString())
            viewModel.error.postValue(null)
        })

        viewModel.mProgressBar.nonNullObserve(viewLifecycleOwner){
            binding.progressBar.isVisible = it
            viewModel.mProgressBar.postValue(null)
        }

        viewModel.isFavorite.nonNullObserve(viewLifecycleOwner){isChecked->
            if (isChecked) save() else delete()
        }
    }

    private fun setIsCheckIngredient(position: Int) {
        viewModel.setIsCheckIngredient(position)
    }

    private fun save(){
        viewModel.saveFavoriteMeal(meal)
    }

    private fun delete(){
        viewModel.deleteFavoriteMeal(meal)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}