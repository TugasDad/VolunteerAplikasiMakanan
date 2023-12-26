package com.capstonebangkit.dishcover.callback

import android.content.Context
import com.capstonebangkit.dishcover.dataclass.FavoriteDataClass
import com.capstonebangkit.dishcover.dataclass.dataFavorite
import com.capstonebangkit.dishcover.sharepref.TokenSharePref
import com.capstonebangkit.dishcover.viewmodel.FavoriteViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteCallback {

    interface FavoriteCallback{
        fun onSuccess(favorite: List<dataFavorite>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getFavorite(callback : FavoriteCallback, context: Context){
        val favoriteService = FavoriteViewModel().apiInterface(context)
        val call : Call<FavoriteDataClass> = favoriteService.getMyFavorite()

        call.enqueue(object : Callback<FavoriteDataClass>{

            override fun onResponse(
                call: Call<FavoriteDataClass>,
                response: Response<FavoriteDataClass>
            ) {
               if(response.isSuccessful){
                   val favoriteResponse : FavoriteDataClass? = response.body()

                   favoriteResponse?.let {
                       if(it.status == 200){
                           callback.onSuccess(it.data)
                       } else {
                           callback.onError(it.status!!,it.message!!)
                       }
                   }
               } else {
                   callback.onError(response.code(), "Error at : ${response.code()}")
               }
            }

            override fun onFailure(call: Call<FavoriteDataClass>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }

        })
    }
}