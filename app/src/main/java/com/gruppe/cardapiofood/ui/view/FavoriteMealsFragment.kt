package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.AnimNextFragment
import com.gruppe.cardapiofood.databinding.FavoriteMealsFragmentBinding
import com.gruppe.cardapiofood.ui.adapter.MealsAdapter
import com.gruppe.cardapiofood.ui.viewmodel.Meal

class FavoriteMealsFragment : Fragment() {

    private var _binding : FavoriteMealsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FavoriteMealsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()

        prepareRecyclerView()
    }

    private fun observer() {

    }

    private fun prepareRecyclerView() {
        binding.recyclerview.apply {
            //Componente de divisão dos itens da recyclerview
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MealsAdapter {
                navToIngredientsFragment(it)
            }
        }
    }

    private fun navToIngredientsFragment(meal: Meal) {
        findNavController().navigate(
            MealsFragmentDirections.actionMealsFragmentToIngredientFragment(meal),
            AnimNextFragment.animOptions
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}