package com.gruppe.cardapiofood.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.model.Meals
import com.gruppe.cardapiofood.ui.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso

class MealsAdapter(private val viewModel : MealsViewModel)
    : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    var dataSet = mutableListOf<Meals>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imgMeal)
        val description: TextView = view.findViewById(R.id.tvMeal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_meals, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Utilizei Elvis Operator para não mostrar valores nulos
        Picasso.get().load(dataSet[position]?.img ?: "").into(holder.image);
        holder.description.text = dataSet[position]?.strMeal ?: ""

        //Capturo evento de click
        holder.itemView.setOnClickListener {
            viewModel.mMealCurrent.postValue(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(data: MutableList<Meals>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }
}