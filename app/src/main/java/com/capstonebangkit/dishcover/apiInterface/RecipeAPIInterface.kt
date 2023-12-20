package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.RecipeDataClass
import retrofit2.Call
import retrofit2.http.GET

interface RecipeAPIInterface {
    @GET("/api/recipe")
    fun getRecipe() : Call<RecipeDataClass>
}