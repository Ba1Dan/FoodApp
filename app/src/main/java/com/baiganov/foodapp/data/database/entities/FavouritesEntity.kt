package com.baiganov.foodapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baiganov.foodapp.models.Result
import com.baiganov.foodapp.util.Constants.Companion.FAVOURITE_RECIPES_TABLE

@Entity(tableName = FAVOURITE_RECIPES_TABLE)
class FavouritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)