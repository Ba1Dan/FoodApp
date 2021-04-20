package com.baiganov.foodapp.data

import android.util.Log
import com.baiganov.foodapp.data.network.FoodRecipesApi
import com.baiganov.foodapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        val data = foodRecipesApi.getRecipes(queries)
        return data
    }
}