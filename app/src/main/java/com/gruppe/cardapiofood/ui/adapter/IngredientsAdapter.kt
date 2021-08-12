package com.gruppe.cardapiofood.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.model.Ingredients
import com.gruppe.cardapiofood.ui.model.Meals
import com.squareup.picasso.Picasso

class IngredientsAdapter() : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    var dataSet = mutableListOf<Pair<Boolean,String>>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cb : CheckBox = view.findViewById(R.id.checkbox)
        val tvIngredients : TextView = view.findViewById(R.id.tvIngredent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_ingredients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cb.isChecked = dataSet[position].first
        holder.tvIngredients.text = dataSet[position].second

        //Seta o val
        holder.cb.setOnClickListener{
            val text = dataSet[position].second
            val isChecked = holder.cb.isChecked
            dataSet.removeAt(position)
            dataSet.add(position,Pair(isChecked,text))
        }
    }

    override fun getItemCount() = dataSet.size

}