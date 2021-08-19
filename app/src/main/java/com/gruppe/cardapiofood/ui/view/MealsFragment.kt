package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.AnimNextFragment
import com.gruppe.cardapiofood.databinding.FragmentMealsBinding
import com.gruppe.cardapiofood.nonNullObserve
import com.gruppe.cardapiofood.showDialogError
import com.gruppe.cardapiofood.ui.adapter.MealsAdapter
import com.gruppe.cardapiofood.ui.viewmodel.Category
import com.gruppe.cardapiofood.ui.viewmodel.Meal
import com.gruppe.cardapiofood.ui.viewmodel.MealsViewModel

class MealsFragment : Fragment() {

    private  var _binding : FragmentMealsBinding? = null
    private val binding get() = _binding!!

    private val args: MealsFragmentArgs by navArgs()
    private lateinit var category: Category

    private val viewModel: MealsViewModel by viewModels()

    companion object {fun newInstance() = MealsFragment()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recupera o item selecionado no Menu de Categorias
        category = args.category

        viewModel.getMealsList(category.title)

        observer()

        prepareRecyclerView()

    }

    private fun observer() {
        viewModel.mMealItemList.nonNullObserve(viewLifecycleOwner, {
            (binding.recyclerview.adapter as MealsAdapter).setData(it)
        })

        viewModel._error.nonNullObserve(viewLifecycleOwner,{
            showDialogError(requireContext(),"Error",it.toString())
            viewModel._error.postValue(null)
        })

        viewModel._mProgressBar.nonNullObserve(viewLifecycleOwner){
            binding.progressBar.isVisible = it
            viewModel._mProgressBar.postValue(null)
        }
    }

    /**
     * Prepara a Recycler View
     */
    private fun prepareRecyclerView() {
        binding.recyclerview.apply {
            //Componente de divisão dos itens da recyclerview
            addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
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