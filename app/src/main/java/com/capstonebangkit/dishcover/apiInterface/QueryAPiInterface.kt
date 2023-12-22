package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.QueryDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QueryAPiInterface {
    @GET("/api/recipe")
    fun getMenuByQuery(@Query("query") query : String?) : Call<QueryDataClass>

}