package com.gruppe.cardapiofood.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientsEntity (
    @PrimaryKey val ingredientId: Long,
    val recipeCreatorId: Long,
    val ingredientName: String
)