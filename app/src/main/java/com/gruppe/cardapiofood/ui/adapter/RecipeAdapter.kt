package com.gruppe.cardapiofood.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.databinding.ItemViewRecipeBinding
import com.gruppe.cardapiofood.ui.model.Ingredient
import com.gruppe.cardapiofood.ui.viewmodel.Recipe

class RecipeAdapter(
    private val context: Activity,
    private val arrayList: List<Ingredient>,
    private val onItemClick: (position: Int) -> Unit,
) : ArrayAdapter<Ingredient>(context, R.layout.item_view_recipe, arrayList) {

    private lateinit var binding: ItemViewRecipeBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_view_recipe, null)

        binding = ItemViewRecipeBinding.bind(view)

        val checkBox = binding.checkbox
        val tvIngredient = binding.tvIngredent

        arrayList?.get(position)?.let { recipe ->
            checkBox.isChecked = recipe.isCheck
            tvIngredient.text = recipe.description

            view.setOnClickListener {
                onItemClick(position)
            }
        }

        return binding.root
    }

    override fun getCount() = arrayList.size


}