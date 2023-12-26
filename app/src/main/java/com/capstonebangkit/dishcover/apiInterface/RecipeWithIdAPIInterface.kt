package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.RecipeWithIdDataClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeWithIdAPIInterface {

    @GET("/api/recipe/{id}")
    suspend fun getRecipe(
        @Header("Authorization") token : String?,
        @Query("id") id : String?
    ) : Response<RecipeWithIdDataClass>
}