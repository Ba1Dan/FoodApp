package com.baiganov.foodapp.data

import com.baiganov.foodapp.data.database.RecipesDao
import com.baiganov.foodapp.data.database.entities.FavouritesEntity
import com.baiganov.foodapp.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.getRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    fun readFavouriteRecipes(): Flow<List<FavouritesEntity>> {
        return recipesDao.getFavouriteRecipes()
    }

    suspend fun insertFavouriteRecipe(favouritesEntity: FavouritesEntity) {
        recipesDao.insertFavouriteRecipes(favouritesEntity)
    }

    suspend fun deleteFavouriteRecipe(favouritesEntity: FavouritesEntity) {
        recipesDao.deleteFavouriteRecipe(favouritesEntity)
    }

    suspend fun deleteAllFavouriteRecipes() {
        recipesDao.deleteAllFavouriteRecipes()
    }
}