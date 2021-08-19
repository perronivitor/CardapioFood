package com.gruppe.cardapiofood.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.databinding.ItemViewMealsBinding
import com.gruppe.cardapiofood.load
import com.gruppe.cardapiofood.ui.viewmodel.Meal


class MealsAdapter(private var dataSet: List<Meal>? = null,
                   private val onItemClick: (meal: Meal) -> Unit)
    : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    private lateinit var binding : ItemViewMealsBinding

    class ViewHolder(binding: ItemViewMealsBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.imgMeal
        val description: TextView = binding.tvMeal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemViewMealsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet?.get(position)?.let { meal ->
            holder.image.load(meal.imgUrl)
            holder.description.text = meal.title
            holder.itemView.setOnClickListener {
                onItemClick(meal)
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun setData(data: List<Meal>) {
        dataSet = data
        notifyDataSetChanged()
    }
}