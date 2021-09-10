package com.gruppe.cardapiofood.ui.viewmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(
    val title: String,
    val imgUrl: String
) : Parcelable