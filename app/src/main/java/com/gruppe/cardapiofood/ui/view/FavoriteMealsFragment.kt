package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.AnimNextFragment
import com.gruppe.cardapiofood.MainActivity
import com.gruppe.cardapiofood.databinding.FavoriteMealsFragmentBinding
import com.gruppe.cardapiofood.nonNullObserve
import com.gruppe.cardapiofood.ui.adapter.FavoriteMealsAdapter
import com.gruppe.cardapiofood.ui.adapter.MealsAdapter
import com.gruppe.cardapiofood.ui.adapter.RecipeAdapter
import com.gruppe.cardapiofood.ui.viewmodel.FavoriteMealsViewModel
import com.gruppe.cardapiofood.ui.viewmodel.Meal
import com.gruppe.cardapiofood.ui.viewmodel.RecipeViewModel

class FavoriteMealsFragment : Fragment() {

    private var _binding: FavoriteMealsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteMealsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteMealsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            (requireActivity() as MainActivity),
            FavoriteMealsViewModel.FavoriteMealsViewModelFactory(requireActivity().application))
            .get(FavoriteMealsViewModel::class.java)


        observer()
        prepareRecyclerView()
    }

    private fun observer() {
        viewModel.mFavoriteMealsList.nonNullObserve(viewLifecycleOwner, {
            (binding.recyclerview.adapter as FavoriteMealsAdapter).setData(it)
        })
        viewModel._mProgressBar.nonNullObserve(viewLifecycleOwner){
            binding.progressBar.isVisible = it
            viewModel._mProgressBar.postValue(null)
        }
    }

    private fun prepareRecyclerView() {
        binding.recyclerview.apply {
            //Componente de divisão dos itens da recyclerview
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FavoriteMealsAdapter {
                navToIngredientsFragment(it)
            }
        }
    }

    private fun navToIngredientsFragment(meal: Meal) {
        findNavController().navigate(
            FavoriteMealsFragmentDirections.actionFavoriteMealsFragmentToIngredientFragment(meal),
            AnimNextFragment.animOptions
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}