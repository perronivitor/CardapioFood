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
import androidx.core.view.isVisible
import com.gruppe.cardapiofood.databinding.FragmentMenuCategoryBinding
import com.gruppe.cardapiofood.nonNullObserve
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.ui.viewmodel.Category


class MenuCategoriaFragment : Fragment() {

    private var _binding : FragmentMenuCategoryBinding? = null
    private val binding get() = _binding!!

    private val vm: MenuCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.menuItemSearch).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menuItemLight -> {
                // TODO
            }
            R.id.menuItemFavorite -> {
                navToFavoriteMealsFragment()
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
        savedInstanceState: Bundle?): View? {
        _binding = FragmentMenuCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        setupObservers()

        vm.getMenuCategoryList()
    }

    private fun setupObservers() {
        vm.mCategoryItemList.nonNullObserve(viewLifecycleOwner) {
            (binding.recyclerview.adapter as MenuCategoriaAdapter).setData(it)
        }

        vm._mProgressBar.nonNullObserve(viewLifecycleOwner){
            binding.progressBar.isVisible = it
            vm._mProgressBar.postValue(null)
        }
    }

    private fun prepareRecyclerView() {
        binding.recyclerview.apply {
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

    private fun navToFavoriteMealsFragment(){
        findNavController().navigate(
            R.id.action_MenuCategoryFragment_to_favoriteMealsFragment,
            null,
            AnimNextFragment.animOptions)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
