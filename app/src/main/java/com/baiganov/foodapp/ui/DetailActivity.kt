package com.baiganov.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.baiganov.foodapp.R
import com.baiganov.foodapp.adapters.PagerAdapter
import com.baiganov.foodapp.data.database.entities.FavouritesEntity
import com.baiganov.foodapp.databinding.ActivityDetailBinding
import com.baiganov.foodapp.ui.fragments.ingredients.IngredientsFragment
import com.baiganov.foodapp.ui.fragments.instructions.InstructionsFragment
import com.baiganov.foodapp.ui.fragments.overview.OverviewFragment
import com.baiganov.foodapp.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0
    private val args by navArgs<DetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.save_to_favourite)
        checkSavedRecipes(menuItem!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favourite && !recipeSaved) {
            saveToFavourite(item)
        } else if (item.itemId == R.id.save_to_favourite && recipeSaved) {
            removeFromFavorite(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeFromFavorite(item: MenuItem) {
        val favoritesEntity =
            FavouritesEntity(
                savedRecipeId,
                args.result
            )
        mainViewModel.deleteFavouriteRecipe(favoritesEntity)
        changeMenuDrawable(item, R.drawable.ic_star_border)
        showSnackBar("Removed from Favorites.")
        recipeSaved = false
    }

    private fun saveToFavourite(item: MenuItem) {
        val favoritesEntity =
            FavouritesEntity(
                0,
                args.result
            )
        mainViewModel.insertFavouriteRecipe(favoritesEntity)
        changeMenuDrawable(item, R.drawable.ic_star)
        showSnackBar("Recipe saved.")
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        mainViewModel.readFavouriteRecipes.observe(this, { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.result.id == args.result.id) {
                        changeMenuDrawable(menuItem, R.drawable.ic_star)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    } else {
                        changeMenuDrawable(menuItem, R.drawable.ic_star_border)
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        })
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()
    }

    private fun changeMenuDrawable(item: MenuItem, drawable: Int) {
        item.setIcon(drawable)
    }
}