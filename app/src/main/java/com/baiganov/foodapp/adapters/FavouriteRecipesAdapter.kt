package com.baiganov.foodapp.adapters

import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baiganov.foodapp.R
import com.baiganov.foodapp.data.database.entities.FavouritesEntity
import com.baiganov.foodapp.databinding.FavoriteRecipesRowLayoutBinding
import com.baiganov.foodapp.ui.fragments.favourites.FavouriteRecipesFragmentDirections
import com.baiganov.foodapp.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.favorite_recipes_row_layout.view.*


class FavouriteRecipesAdapter(
    private val requireActivity: FragmentActivity
) : RecyclerView.Adapter<FavouriteRecipesAdapter.FavouriteViewHolder>(), ActionMode.Callback {

    private var favoriteRecipes = emptyList<FavouritesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val selectedRecipe = favoriteRecipes[position]
        holder.bind(selectedRecipe, requireActivity, this)
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    class FavouriteViewHolder(private val binding: FavoriteRecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            favoritesEntity: FavouritesEntity,
            requireActivity: FragmentActivity,
            callback: ActionMode.Callback
        ) {
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
            /**
             * Single Click Listener
             * */
            binding.root.setOnClickListener {
                val action =
                    FavouriteRecipesFragmentDirections.actionFavouriteRecipesFragmentToDetailActivity(
                        favoritesEntity.result
                    )
                binding.root.findNavController().navigate(action)
            }

            /**
             * Long Click Listener
             * */
            binding.root.setOnLongClickListener {
                requireActivity.startActionMode(callback)
                true
            }
        }

        companion object {
            fun from(parent: ViewGroup): FavouriteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return FavouriteViewHolder(binding)
            }
        }
    }

    /**
     * Contextual Action Mode
     * */
    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favourite_contextual_mode, menu)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {
    }

    fun setData(newFavoriteRecipes: List<FavouritesEntity>) {
        val favoriteRecipesDiffUtil =
            RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }
}