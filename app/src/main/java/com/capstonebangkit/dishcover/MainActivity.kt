package com.capstonebangkit.dishcover

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstonebangkit.dishcover.databinding.ActivityMainBinding
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.viewmodel.RecipeViewModel
import com.capstonebangkit.dishcover.viewmodel.RecipeWithIdViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val recipeViewModel : RecipeViewModel by viewModels()
    private val recipeWithIdViewModel : RecipeWithIdViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_pantry,
                R.id.navigation_camera
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // for test
        // java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
        // for this problem, get a json data and parse a json
        recipeViewModel.dataRecipe.observe(this, Observer {recipes ->
            // Log.d("Recipe Activity", "Recipes : $recipes")
            for(recipe in recipes){
                Log.d("Id", recipe.id.toString())
                Log.d("Name", recipe.name.toString())
                Log.d("Description", recipe.description.toString())
                Log.d("urlPhoto",recipe.urlImage.toString())
            }
        })
        recipeViewModel.getDataRecipe()

        recipeWithIdViewModel.dataRecipeWithId.observe(this, Observer {recipeWithId ->
            for(recipe in recipeWithId){
                Log.d("Id_Recipe", recipe.id.toString())
                Log.d("Name_Recipe", recipe.name.toString())
                Log.d("Description_Recipe", recipe.description.toString())
                Log.d("urlPhoto_Recipe",recipe.urlImage.toString())
            }
        })
        recipeWithIdViewModel.getDataRecipe()

        recipeWithIdViewModel.message.observe(this, Observer {message ->
            Log.w("Message", message.toString())
        })


    }
}