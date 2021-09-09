package com.gruppe.cardapiofood.ui.viewmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val title: String,
    @SerializedName("strCategoryThumb")
    var imgUrl: String = ""
) : Parcelable