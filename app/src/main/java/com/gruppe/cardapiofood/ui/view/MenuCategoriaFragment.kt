package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.AnimNextFragment
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.adapter.MenuCategoriaAdapter
import com.gruppe.cardapiofood.ui.model.Categories
import com.gruppe.cardapiofood.ui.viewmodel.MenuCategoryViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuCategoriaFragment : Fragment() {

    private val viewModel: MenuCategoryViewModel by viewModels()
    private lateinit var viewFragment: View
    private lateinit var adapter: MenuCategoriaAdapter
    private lateinit var recyclerView: RecyclerView
    private var isInstanceCreate: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.fragment_menu_category, container, false)
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        isInstanceCreate = savedInstanceState != null
        viewModel.getMenuCategoryList()
        observers()

    }

    override fun onResume() {
        viewModel.getMenuCategoryList()
        super.onResume()
    }

    private fun observers() {
        viewModel.mCategoryItemList.observe(viewLifecycleOwner, {
            if (it == null){return@observe}
            if (!isInstanceCreate) prepareRecyclerView(it) else adapter.setData(it)
        })

        viewModel.mCategoryCurrent.observe(viewLifecycleOwner,{category->
            if (category != null){
                //Passando dados por Safe Args
                val directions =
                    MenuCategoriaFragmentDirections
                        .actionMenuCategoryFragmentToMealsFragment(category)
                findNavController()
                    .navigate(directions,AnimNextFragment.animOptions)
            }
        })
    }

    private fun bindViews() {
        recyclerView = viewFragment.findViewById(R.id.recyclerview)
    }

    /**
     * Prepara a Recycler View
     */
    private fun prepareRecyclerView(itens : MutableList<Categories?>) {
        adapter = MenuCategoriaAdapter(viewModel)
        adapter.dataSet.addAll(itens)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


}