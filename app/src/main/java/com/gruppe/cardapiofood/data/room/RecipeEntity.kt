package com.gruppe.cardapiofood.data.room

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gruppe.cardapiofood.ui.model.Ingredient
import java.lang.reflect.Type
import java.util.*

@Entity(tableName = "table_favorite_meals",indices = [Index(value = ["id","title"], unique = true)])
data class RecipeEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,
    @ColumnInfo(name = "favorite")
    var isFavorite :Boolean,
    @ColumnInfo(name = "prepareMode")
    val prepareMode : String,
    @TypeConverters(IngredientsTypeConverter::class)
    @ColumnInfo(name = "ingredients")
    val ingredients : List<String>
)

class IngredientsTypeConverter{

    var gson = Gson()

    @TypeConverter
    fun stringToIngredientsList(data : String) : List<String>{
        if (data == null){ return Collections.emptyList() }
        val listType : Type = object : TypeToken<List<Ingredient>>(){}.type
        return gson.fromJson(data,listType)
    }

    @TypeConverter
    fun ingredientsListToString(someObjects: List<String>):String {
        return gson.toJson(someObjects)
    }

}