package com.gruppe.cardapiofood.ui.viewmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table_favorite_meals")
@Parcelize
data class Meal(
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @PrimaryKey()
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,
    @ColumnInfo(name = "favorite")
    var isFavorite : Boolean = false
) : Parcelable