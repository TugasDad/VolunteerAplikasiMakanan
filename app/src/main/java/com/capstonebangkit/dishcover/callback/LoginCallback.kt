package com.capstonebangkit.dishcover.callback

import android.util.Log
import com.capstonebangkit.dishcover.apiInterface.LoginAPIInterface
import com.capstonebangkit.dishcover.dataclass.LoginDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCallback(private val loginInterface : LoginAPIInterface) {

    fun login(username : String?, password : String?, onResult : (Result<String>) -> Unit){
        loginInterface.LoginDataUser(username, password).enqueue(object  : Callback<LoginDataClass> {
            override fun onResponse(
                call: Call<LoginDataClass>,
                response: Response<LoginDataClass>
            ) {
                if(response.isSuccessful){
                    val token = response.body()?.token
                    Log.d("token",token.toString())
                    onResult(Result.success(token ?: ""))
                } else {
                    Log.e("Response Get OnError", response.code().toString())
                }
            }

            override fun onFailure(call: Call<LoginDataClass>, t: Throwable) {
                Log.e("Network request fail", t.toString())
            }

        })
    }
}