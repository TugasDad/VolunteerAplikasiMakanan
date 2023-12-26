package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.FavoriteDataClass
import com.capstonebangkit.dishcover.dataclass.FavoritePostDataClass
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoritAPIInterface {

    @GET("/api/myfavourite")
    fun getMyFavorite() : Call<FavoriteDataClass>

    @FormUrlEncoded
    @POST("/api/myfavourite/{recipe_id}")
    fun postFavoriteRecipe(@Field("recipe_id") recipe_id : String?) : Call<FavoritePostDataClass>
}