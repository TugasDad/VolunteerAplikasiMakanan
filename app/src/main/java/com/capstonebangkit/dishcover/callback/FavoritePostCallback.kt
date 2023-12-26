package com.capstonebangkit.dishcover.callback

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.dataclass.FavoritePostDataClass
import com.capstonebangkit.dishcover.dataclass.responsePostDataClass
import com.capstonebangkit.dishcover.viewmodel.FavoritePostViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritePostCallback : ViewModel(){

    interface FavoritePost{
        fun onSuccess(favoritePost : List<responsePostDataClass>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun postFavorite(callback : FavoritePost, context : Context){
        val favoritePostService = FavoritePostViewModel().apiInterface(context)
        val call : Call<FavoritePostDataClass> = favoritePostService.postFavoriteRecipe(FavoritePostViewModel().recipe_id)

        call.enqueue(object : Callback<FavoritePostDataClass>{
            override fun onResponse(
                call: Call<FavoritePostDataClass>,
                response: Response<FavoritePostDataClass>
            ) {
                if(response.isSuccessful){
                   val favoritePostResponse : FavoritePostDataClass? = response.body()
                    Log.d("FavoritePost recipe_id ${FavoritePostViewModel().recipe_id}", favoritePostResponse?.data.toString())

                    favoritePostResponse?.let {
                        if(it.status == 200){
                            callback.onSuccess(it.data)
                        } else {
                            callback.onError(it.status!!,it.message!!)
                        }
                    }

                } else {
                    callback.onError(response.code(), "Error at : ${response.code()}")
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<FavoritePostDataClass>, t: Throwable) {
                callback.onError(0,"Fail at : ${t.message}")
            }

        })
    }
}