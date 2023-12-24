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
import com.capstonebangkit.dishcover.sharepref.TokenSharePref
import com.capstonebangkit.dishcover.viewmodel.FavoriteViewModel
import com.capstonebangkit.dishcover.viewmodel.KeywordViewModel
import com.capstonebangkit.dishcover.viewmodel.LoginViewModel
import com.capstonebangkit.dishcover.viewmodel.QueryViewModel
import com.capstonebangkit.dishcover.viewmodel.RecipeViewModel
import com.capstonebangkit.dishcover.viewmodel.RecipeWithIdViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val recipeViewModel : RecipeViewModel by viewModels()
    private val recipeWithIdViewModel : RecipeWithIdViewModel by viewModels()
    private val favorite : FavoriteViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    private val queryData : QueryViewModel by viewModels()
    private val keywordData : KeywordViewModel by viewModels()

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

        loginViewModel.username = "endangkus"
        loginViewModel.password = "123456"
        // login
        loginViewModel.DataLogin.observe(this, Observer {token ->
            Log.d("Token Get : ", token.toString())
            // in here maybe you can store a token
            TokenSharePref(this).setToken(token)
        })
        loginViewModel.loginDataUser()

        loginViewModel.message.observe(this, Observer {message ->
            Log.w("LoginViewModel Message", message.toString())
        })


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

        // autorized permission (for access this, you must get token)
        recipeWithIdViewModel.dataRecipeWithId.observe(this, Observer {recipeWithId ->
            for(recipe in recipeWithId){
                Log.d("Id_Recipe", recipe.id.toString())
                Log.d("Name_Recipe", recipe.name.toString())
                Log.d("Description_Recipe", recipe.description.toString())
                Log.d("urlPhoto_Recipe",recipe.urlImage.toString())
            }
        })
        recipeWithIdViewModel.getDataRecipe(this)

        recipeWithIdViewModel.message.observe(this, Observer {message ->
            Log.w("RecipeWithId Message", message.toString())
        })

        // favorite test
        favorite.dataFavorite.observe(this, Observer {favoriteData ->
            for(favorite in favoriteData){
                Log.d("Id_favorite", favorite.id.toString())
                Log.d("User_Id", favorite.userId.toString())
                Log.d("id",favorite.recipe_id.id.toString())
                Log.d("name", favorite.recipe_id.name.toString())
                Log.d("description",favorite.recipe_id.description.toString())
                Log.d("ingredients",favorite.recipe_id.ingredients.toString())
                Log.d("step", favorite.recipe_id.step.toString())
                Log.d("urlimage",favorite.recipe_id.urlimage.toString())
            }
        })
        favorite.getDataFavorite(this)

        favorite.message.observe(this, Observer {message ->
            Log.w("Favorite Message", message.toString())
        })

        // get Query ayam
        queryData.queryData.observe(this, Observer {query ->
            for(dataQuery in query){
                Log.d("Id_query", dataQuery.id.toString())
                Log.d("Name_query", dataQuery.name.toString())
                Log.d("Description_query", dataQuery.description.toString())
                Log.d("urlPhoto_query",dataQuery.urlImage.toString())
            }
        })
        queryData.getQueryResult()

        // get Keyword ayam,sayur
        keywordData.keyworddata.observe(this, Observer { keyword ->
            for(dataKeyword in keyword){
                Log.d("Id_keyword", dataKeyword.id.toString())
                Log.d("Name_keyword", dataKeyword.name.toString())
                Log.d("Description_keyword", dataKeyword.description.toString())
                Log.d("urlPhoto_keyword",dataKeyword.urlImage.toString())
            }
        })
        keywordData.getKeywordResult()

        /*keywordData.message.observe(this, Observer {message ->
            Log.w("Message", message.toString())
        })

        keywordData.error.observe(this, Observer {message ->
            Log.w("Error : ", message.toString())
        })*/


    }
}