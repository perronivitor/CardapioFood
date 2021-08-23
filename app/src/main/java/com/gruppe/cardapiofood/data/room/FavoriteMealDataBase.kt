package com.gruppe.cardapiofood.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gruppe.cardapiofood.ui.viewmodel.Meal


@Database(
    entities = [Meal::class],
    version = 2,
    exportSchema = false
)
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