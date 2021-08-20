package com.gruppe.cardapiofood.ui.viewmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table_favorite_meals")
@Parcelize
data class Meal(
    @ColumnInfo(name = "id")
    val id: String,
    val title: String,
    val imgUrl: String,
    var isFavorite : Boolean = false
) : Parcelable