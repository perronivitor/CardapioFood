package com.gruppe.cardapiofood.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.load
import com.gruppe.cardapiofood.ui.viewmodel.Category

class MenuCategoriaAdapter(
    private var dataSet: List<Category>? = null,
    private val onItemClick: (category: Category) -> Unit
) : RecyclerView.Adapter<MenuCategoriaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imgCategory)
        val description: TextView = view.findViewById(R.id.tvDescriptionCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_menu_category,
                    parent,
                    false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet?.get(position)?.let { category ->
            holder.image.load(category.imgUrl)
            holder.description.text = category.title
            holder.itemView.setOnClickListener {
                onItemClick(category)
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun setData(data: List<Category>) {
        dataSet = data
        notifyDataSetChanged()
    }
}