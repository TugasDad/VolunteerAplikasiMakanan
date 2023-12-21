package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.LoginDataClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPIInterface {
    @FormUrlEncoded
    @POST("/api/user/login")
    fun LoginDataUser(
        @Field("username") username : String?,
        @Field("password") password : String?
    ) : Call<LoginDataClass>
}