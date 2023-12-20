package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.FavoriteDataClass
import retrofit2.Call
import retrofit2.http.GET

interface FavoritAPIInterface {

    @GET("/api/myfavourite")
    fun getMyFavorite() : Call<FavoriteDataClass>
}