package com.gruppe.cardapiofood.ui.viewmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(
    val id: String,
    val title: String,
    val imgUrl: String,
) : Parcelable