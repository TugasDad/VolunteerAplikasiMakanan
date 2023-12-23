package com.capstonebangkit.dishcover.callback

import android.util.Log
import com.capstonebangkit.dishcover.apiInterface.LoginAPIInterface
import com.capstonebangkit.dishcover.dataclass.LoginDataClass
import com.capstonebangkit.dishcover.dataclass.QueryData
import com.capstonebangkit.dishcover.viewmodel.LoginViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginCallback(){

    interface LoginCallback{
        fun onSuccess(Response: String?)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getLoginResponse(callback: LoginCallback){
        val loginService = LoginViewModel().apiInterface
        val call : Call<LoginDataClass> = loginService.postLogin(LoginViewModel().username,LoginViewModel().password)

        call.enqueue(object : Callback<LoginDataClass>{
            override fun onResponse(
                call: Call<LoginDataClass>,
                response: Response<LoginDataClass>
            ) {
                if(response.isSuccessful){
                    val loginResponse : LoginDataClass? = response.body()
                    Log.d("Login Response ", loginResponse?.token.toString())

                    loginResponse?.let {
                       // let a get token
                        callback.onSuccess(it.token)
                    }
                } else {
                    callback.onError(response.code(),"Error at : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginDataClass>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }

        })
    }

}