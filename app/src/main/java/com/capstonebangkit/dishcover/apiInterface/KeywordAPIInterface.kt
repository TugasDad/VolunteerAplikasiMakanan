package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.KeywordDataClass
import com.capstonebangkit.dishcover.dataclass.QueryDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KeywordAPIInterface {
    @GET("/api/recipe")
    fun getMenuByKeyword(@Query("keywords") keywords : String?) : Call<KeywordDataClass>
}