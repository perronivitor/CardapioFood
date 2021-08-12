package com.gruppe.cardapiofood.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.model.Categories
import com.gruppe.cardapiofood.ui.viewmodel.MenuCategoryViewModel
import com.squareup.picasso.Picasso

class MenuCategoriaAdapter(private val viewModel: MenuCategoryViewModel)
    : RecyclerView.Adapter<MenuCategoriaAdapter.ViewHolder>() {

    var dataSet = mutableListOf<Categories?>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imgCategory)
        val description: TextView = view.findViewById(R.id.tvDescriptionCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_menu_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Utilizei Elvis Operator para não mostrar valores nulos

        Picasso.get().load(dataSet[position]?.img?:"").into(holder.image);
        holder.description.text = dataSet[position]?.strCategory ?: ""

        //Capturo evento de click
        holder.itemView.setOnClickListener {
            viewModel.mCategoryCurrent.postValue(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(data: MutableList<Categories?>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }
}