package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.adapter.MenuCategoriaAdapter
import com.gruppe.cardapiofood.ui.viewmodel.MenuCategoryViewModel
import android.view.MenuInflater
import androidx.core.view.isVisible
import com.gruppe.cardapiofood.databinding.FragmentMenuCategoryBinding
import com.gruppe.cardapiofood.navigateWithAnimations
import com.gruppe.cardapiofood.nonNullObserve
import com.gruppe.cardapiofood.retrofit.Resultado
import com.gruppe.cardapiofood.showDialogError
import com.gruppe.cardapiofood.ui.viewmodel.Category
import java.net.ConnectException


class MenuCategoriaFragment : Fragment() {

    private var _binding: FragmentMenuCategoryBinding? = null
    private val binding get() = _binding!!

    private val vm: MenuCategoryViewModel by viewModels()

    private val navControler by lazy {
        findNavController()
    }

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
        when (item.itemId) {
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
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {

        vm.mCategoryList.nonNullObserve(viewLifecycleOwner){ result ->
            binding.progressBar.isVisible = true
            try {
                result?.let { r ->
                    when (r) {
                        is Resultado.Sucesso -> {
                            r.dado?.let { category ->
                                setDataAdapter(category)
                            }
                        }
                        is Resultado.Erro -> {
                            showDialogError(requireContext(),"Error",r.exception.message.toString())
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("MenuFrag", "getMenuCategoryList: Error->${e.message} ")
                showDialogError(requireContext(),"Error","Erro desconhecido!")
            } finally {
                binding.progressBar.isVisible = false
            }
        }
    }

        private fun setDataAdapter(category : List<Category>){
        (binding.recyclerview.adapter as MenuCategoriaAdapter).setData(category)
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
        navControler.navigateWithAnimations(
            MenuCategoriaFragmentDirections.actionMenuCategoryFragmentToMealsFragment(category)
        )
    }

    private fun navToFavoriteMealsFragment() {
        navControler.navigateWithAnimations(R.id.action_MenuCategoryFragment_to_favoriteMealsFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
