package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.adapter.MealsAdapter
import com.gruppe.cardapiofood.ui.adapter.MenuCategoriaAdapter
import com.gruppe.cardapiofood.ui.model.Categories
import com.gruppe.cardapiofood.ui.model.Meals
import com.gruppe.cardapiofood.ui.viewmodel.MealsViewModel

class MealsFragment : Fragment() {

    private val args: MealsFragmentArgs by navArgs()
    private lateinit var meals: Categories

    //Componentes
    private lateinit var viewFragment: View
    private val viewModel: MealsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealsAdapter
    private var isInstanced: Boolean = false

    companion object {
        fun newInstance() = MealsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewFragment = inflater.inflate(R.layout.fragment_meals, container, false)
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recupera o item selecionado no Menu de Categorias
        meals = args.meals

        recyclerView = viewFragment.findViewById(R.id.recyclerview)

        viewModel.getMeals(meals.strCategory)

        isInstanced = savedInstanceState != null

        observer()

    }

    private fun observer() {
        viewModel.mMealsList.observe(viewLifecycleOwner, {
            if (it == null) { return@observe }
            if (!isInstanced) prepareRecyclerView(it) else adapter.setData(it)
        })
    }

    /**
     * Prepara a Recycler View
     */
    private fun prepareRecyclerView(itens: MutableList<Meals>) {
        adapter = MealsAdapter()
        adapter.dataSet.addAll(itens)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //Componente de divisão dos itens da recyclerview
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
    }
}