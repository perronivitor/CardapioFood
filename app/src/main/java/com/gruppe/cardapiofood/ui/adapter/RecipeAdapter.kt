package com.gruppe.cardapiofood.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.databinding.ItemViewRecipeBinding
import com.gruppe.cardapiofood.ui.model.Ingredient

class RecipeAdapter(private var dataSet: List<Ingredient>? = null,
                    private val onItemClick: (position : Int) -> Unit)
    : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private lateinit var binding : ItemViewRecipeBinding

    class ViewHolder(binding: ItemViewRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        val cb : CheckBox = binding.checkbox
        val tvIngredients : TextView = binding.tvIngredent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemViewRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet?.get(position)?.let { ingredient ->
            holder.cb.isChecked = ingredient.isCheck
            holder.tvIngredients.text = ingredient.description
            holder.itemView.setOnClickListener {
                onItemClick(position)
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun setData(data : List<Ingredient>){
        dataSet = data
        notifyDataSetChanged()
    }
}