package com.gruppe.cardapiofood.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryData(
    var idCategory: String = "",
    var strCategory: String = "",
    @SerializedName("strCategoryThumb")
    var imgUrl: String = "",
) :Parcelable