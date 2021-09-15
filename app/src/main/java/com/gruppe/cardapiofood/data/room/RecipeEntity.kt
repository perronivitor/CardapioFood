package com.gruppe.cardapiofood.data.room

import androidx.room.*

@Entity(tableName = "table_favorite_recipe",indices = [Index(value = ["recipeId","title"], unique = true)])
data class RecipeEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipeId")
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,
    @ColumnInfo(name = "favorite")
    var isFavorite :Boolean,
    @ColumnInfo(name = "prepareMode")
    val prepareMode : String
)

