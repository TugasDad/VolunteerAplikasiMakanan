package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.RecipeWithIdDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeWithIdAPIInterface {

    @GET("/api/recipe/{id}")
    fun getRecipe(@Path("id") id : String?) : Call<RecipeWithIdDataClass>
}