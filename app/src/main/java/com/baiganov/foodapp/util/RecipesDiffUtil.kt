package com.baiganov.foodapp.util

import androidx.recyclerview.widget.DiffUtil
import com.baiganov.foodapp.models.Result

class RecipesDiffUtil(
    private val newList: List<Result>,
    private val oldList: List<Result>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] === oldList[oldItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }
}