package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.AnimNextFragment
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.adapter.MenuCategoriaAdapter
import com.gruppe.cardapiofood.ui.viewmodel.MenuCategoryViewModel

import android.view.MenuInflater
import com.gruppe.cardapiofood.nonNullObserve
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.ui.viewmodel.Category


class MenuCategoriaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val vm: MenuCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menuItemLight -> {
                // TODO
            }
            R.id.menuItemFavorite -> {
                // TODO
            }
            R.id.menuItemSearch -> {
                // TODO
            }
            R.id.menuItemSettings -> {
                // TODO
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_menu_category,
            container,
            false
        ).apply {
            recyclerView = findViewById(R.id.recyclerview)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        setupObservers()

        vm.getMenuCategoryList()
    }

    private fun setupObservers() {
        vm.mCategoryItemList.nonNullObserve(viewLifecycleOwner, {
            (recyclerView.adapter as MenuCategoriaAdapter).setData(it)
        })
    }

    private fun prepareRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MenuCategoriaAdapter {
                navToMealsFragment(it)
            }
        }
    }

    private fun navToMealsFragment(category: Category) {
        findNavController().navigate(
            MenuCategoriaFragmentDirections.actionMenuCategoryFragmentToMealsFragment(category),
            AnimNextFragment.animOptions
        )
    }
}
