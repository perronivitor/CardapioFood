package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.adapter.IngredientsAdapter
import com.gruppe.cardapiofood.ui.model.IngredientsData
import com.gruppe.cardapiofood.ui.viewmodel.IngredientsViewModel
import com.gruppe.cardapiofood.ui.viewmodel.Meal
import com.squareup.picasso.Picasso

class IngredientFragment : Fragment() {

    private val args : IngredientFragmentArgs by navArgs()
    private lateinit var meal : Meal

    //Componentes
    private lateinit var viewFragment: View
    private val viewModel: IngredientsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IngredientsAdapter
    private var isInstanced: Boolean = false
    private lateinit var imgMeal : ImageView
    private lateinit var tvMeal : TextView
    private lateinit var tvInstructions : TextView

    companion object { fun newInstance() = IngredientFragment() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        viewFragment = inflater.inflate(R.layout.fragment_ingredients, container, false)
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meal = args.meal
        viewModel.getIngredients(meal.title)

        isInstanced = savedInstanceState != null

        bindViews()
        observer()
    }

    private fun bindViews() {
        recyclerView = viewFragment.findViewById(R.id.recyclerview)
        imgMeal = viewFragment.findViewById(R.id.imgMeal)
        tvMeal = viewFragment.findViewById(R.id.tvMeal)
        tvInstructions = viewFragment.findViewById(R.id.tvPrepareMode)
    }

    private fun observer() {
        viewModel.mIngredients.observe(viewLifecycleOwner,{
            if (it == null){return@observe}
            if (!isInstanced){
                prepareRecyclerView(prepareDataForRecyclerView(it))
                // Seta foto da receita
                Picasso.get().load(it.img).into(imgMeal)
                //Seto text nome receita
                tvMeal.text = meal.title
                tvInstructions.text=it.strInstructions
            }
        })
    }
    private fun prepareDataForRecyclerView(ing: IngredientsData):List<Pair<Boolean,String>>{
        val ingredients = mutableListOf<Pair<Boolean,String>>()
        ingredients.add(Pair(false , ((ing.strMeasure1 +" "+ ing.strIngredient1))))
        ingredients.add(Pair(false , ((ing.strMeasure2 +" "+ ing.strIngredient2))))
        ingredients.add(Pair(false , ((ing.strMeasure3 +" "+ ing.strIngredient3))))
        ingredients.add(Pair(false , ((ing.strMeasure4 +" "+ ing.strIngredient4))))
        ingredients.add(Pair(false , ((ing.strMeasure5 +" "+ ing.strIngredient5))))
        ingredients.add(Pair(false , ((ing.strMeasure6 +" "+ ing.strIngredient6))))
        ingredients.add(Pair(false , ((ing.strMeasure7 +" "+ ing.strIngredient7))))
        ingredients.add(Pair(false , ((ing.strMeasure8 +" "+ ing.strIngredient8))))
        ingredients.add(Pair(false , ((ing.strMeasure9 +" "+ ing.strIngredient9))))
        ingredients.add(Pair(false , ((ing.strMeasure10 +" "+ ing.strIngredient10))))
        ingredients.add(Pair(false , ((ing.strMeasure11 +" "+ ing.strIngredient11))))
        ingredients.add(Pair(false , ((ing.strMeasure12 +" "+ ing.strIngredient12))))
        ingredients.add(Pair(false , ((ing.strMeasure13 +" "+ ing.strIngredient13))))
        ingredients.add(Pair(false , ((ing.strMeasure14 +" "+ ing.strIngredient14))))
        ingredients.add(Pair(false , ((ing.strMeasure15 +" "+ ing.strIngredient15))))
        return ingredients.filterIndexed { _, p -> !p.second.trim().isNullOrEmpty()}
    }

    private fun prepareRecyclerView(itens: List<Pair<Boolean,String>>) {
        adapter = IngredientsAdapter()
        adapter.dataSet.addAll(itens)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

}