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

class MenuCategoriaAdapter : RecyclerView.Adapter<MenuCategoriaAdapter.ViewHolder>() {

    var dataSet = mutableListOf<Categories?>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.imgCategory)
        val description : TextView = view.findViewById(R.id.tvDescriptionCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_menu_category,parent)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Utilizei Elvis Operator para não mostrar valores nulos

        val url = Uri.parse(dataSet[position]?.img?:"")
        holder.image.setImageURI(url)
        holder.image.setImageURI(dataSet[position]?.img as Uri)
        holder.description.text = dataSet[position]?.strCategory?:""
    }

    override fun getItemCount() = dataSet.size
}