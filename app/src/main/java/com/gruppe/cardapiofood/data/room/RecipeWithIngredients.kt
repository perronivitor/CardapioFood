package com.gruppe.cardapiofood.data.room

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded val recipe : RecipeEntity,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeCreatorId"
    )
    val ingredient: List<IngredientsEntity>
)