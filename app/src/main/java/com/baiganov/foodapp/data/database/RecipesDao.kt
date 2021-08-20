package com.baiganov.foodapp.data.database

import androidx.room.*
import com.baiganov.foodapp.data.database.entities.FavouritesEntity
import com.baiganov.foodapp.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteRecipes(favouritesEntity: FavouritesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun getRecipes(): Flow<List<RecipesEntity>>

    @Query("SELECT * FROM favourites_recipes_table ORDER BY id ASC")
    fun getFavouriteRecipes(): Flow<List<FavouritesEntity>>

    @Delete
    fun deleteFavouriteRecipe(favouritesEntity: FavouritesEntity)

    @Query("DELETE FROM favourites_recipes_table")
    fun deleteAllFavouriteRecipes()
}