package com.gruppe.cardapiofood.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IngredientsTypeConverter::class)
abstract class FavoriteMealDataBase :RoomDatabase(){
    abstract val favoriteDAO: FavoriteDAO

    companion object {

        @Volatile
        private var INSTANCE: FavoriteMealDataBase? = null

        fun getInstance(context: Context): FavoriteMealDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteMealDataBase::class.java,
                        "favorite_meals_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}