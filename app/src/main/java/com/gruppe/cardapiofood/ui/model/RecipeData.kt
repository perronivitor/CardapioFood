package com.gruppe.cardapiofood.ui.model

import com.google.gson.annotations.SerializedName

data class RecipeData(
    var idMeal: String,
    var strMeal: String,
    var strInstructions: String,
    @SerializedName("strMealThumb")
    var img: String,
    var strIngredient1: String,
    var strIngredient2: String,
    var strIngredient3: String,
    var strIngredient4: String,
    var strIngredient5: String,
    var strIngredient6: String,
    var strIngredient7: String,
    var strIngredient8: String,
    var strIngredient9: String,
    var strIngredient10: String,
    var strIngredient11: String,
    var strIngredient12: String,
    var strIngredient13: String,
    var strIngredient14: String,
    var strIngredient15: String,
    var strMeasure1: String,
    var strMeasure2: String,
    var strMeasure3: String,
    var strMeasure4: String,
    var strMeasure5: String,
    var strMeasure6: String,
    var strMeasure7: String,
    var strMeasure8: String,
    var strMeasure9: String,
    var strMeasure10: String,
    var strMeasure11: String,
    var strMeasure12: String,
    var strMeasure13: String,
    var strMeasure14: String,
    var strMeasure15: String,
) {
    fun getIngredientsList(): List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        ingredients.add(Ingredient(false, "$strMeasure1 $strIngredient1"))
        ingredients.add(Ingredient(false, "$strMeasure2 $strIngredient2"))
        ingredients.add(Ingredient(false, "$strMeasure3 $strIngredient3"))
        ingredients.add(Ingredient(false, "$strMeasure4 $strIngredient4"))
        ingredients.add(Ingredient(false, "$strMeasure5 $strIngredient5"))
        ingredients.add(Ingredient(false, "$strMeasure6 $strIngredient6"))
        ingredients.add(Ingredient(false, "$strMeasure7 $strIngredient7"))
        ingredients.add(Ingredient(false, "$strMeasure8 $strIngredient8"))
        ingredients.add(Ingredient(false, "$strMeasure9 $strIngredient9"))
        ingredients.add(Ingredient(false, "$strMeasure10 $strIngredient10"))
        ingredients.add(Ingredient(false, "$strMeasure11 $strIngredient11"))
        ingredients.add(Ingredient(false, "$strMeasure12 $strIngredient12"))
        ingredients.add(Ingredient(false, "$strMeasure13 $strIngredient13"))
        ingredients.add(Ingredient(false, "$strMeasure14 $strIngredient14"))
        ingredients.add(Ingredient(false, "$strMeasure15 $strIngredient15"))
        return ingredients.filterIndexed { _, i ->
            !i.description.isNullOrBlank() && i.description.trim() != "null"
        }
    }
}