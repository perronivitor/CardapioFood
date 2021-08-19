package com.gruppe.cardapiofood.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealData(
    val idMeal: String = "",
    val strMeal: String = "",
    @SerializedName("strMealThumb")
    val imgUrl: String = ""
) : Parcelable