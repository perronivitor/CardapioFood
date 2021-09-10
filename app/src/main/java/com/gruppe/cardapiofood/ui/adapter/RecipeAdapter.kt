package com.gruppe.cardapiofood.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.databinding.ItemViewRecipeBinding
import com.gruppe.cardapiofood.ui.model.Ingredient

class RecipeAdapter(
    private val context: Activity,
    private val arrayList: List<String>
    ) : ArrayAdapter<String>(context, R.layout.item_view_recipe, arrayList) {

    private lateinit var binding: ItemViewRecipeBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_view_recipe, null)

        binding = ItemViewRecipeBinding.bind(view)

        val checkBox = binding.checkbox
        val tvIngredient = binding.tvIngredent

        arrayList?.get(position)?.let { recipeTitle ->
            checkBox.isChecked = false
            tvIngredient.text = recipeTitle

            view.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
        }

        return binding.root
    }

    override fun getCount() = arrayList.size


}