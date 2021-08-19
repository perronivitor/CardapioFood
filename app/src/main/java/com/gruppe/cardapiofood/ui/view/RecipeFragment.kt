package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.databinding.FragmentRecipeBinding
import com.gruppe.cardapiofood.load
import com.gruppe.cardapiofood.nonNullObserve
import com.gruppe.cardapiofood.showDialogError
import com.gruppe.cardapiofood.ui.adapter.RecipeAdapter
import com.gruppe.cardapiofood.ui.viewmodel.RecipeViewModel
import com.gruppe.cardapiofood.ui.viewmodel.Meal

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private val args: RecipeFragmentArgs by navArgs()
    private lateinit var meal: Meal

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meal = args.meal
        viewModel.getIngredients(meal.title)

        binding.imgMeal.load(meal.imgUrl)
        binding.tvMeal.text = meal.title

        observer()
        prepareRecyclerView()

    }

    private fun observer() {
        viewModel.mMealIngredientItemList.nonNullObserve(viewLifecycleOwner, {
            (binding.recyclerview.adapter as RecipeAdapter).setData(it.ingredients)
            binding.tvPrepareMode.text = it.prepareMode
        })

        viewModel.error.nonNullObserve(viewLifecycleOwner,{
            showDialogError(requireContext(),"Error",it.toString())
            viewModel.error.postValue(null)
        })

        viewModel._mProgressBar.nonNullObserve(viewLifecycleOwner){
            binding.progressBar.isVisible = it
            viewModel._mProgressBar.postValue(null)
        }
    }

    private fun prepareRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecipeAdapter { positionReturn ->
                setIsCheckIngredient(positionReturn)
            }
        }
    }

    private fun setIsCheckIngredient(position: Int) {
        viewModel.setIsCheckIngredient(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}