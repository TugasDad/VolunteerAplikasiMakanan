package com.capstonebangkit.dishcover.apiInterface

import com.capstonebangkit.dishcover.dataclass.SignUpDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpAPIInterface {
    @FormUrlEncoded
    @POST("/api/user/signup")
    suspend fun signUpDataUser(
        @Body dataUser : SignUpDataClass
    ) : Response<SignUpDataClass>
}