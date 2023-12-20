package com.capstonebangkit.dishcover.callback

import android.util.Log
import com.capstonebangkit.dishcover.apiInterface.RecipeAPIInterface
import com.capstonebangkit.dishcover.dataclass.RecipeDataClass
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import com.capstonebangkit.dishcover.viewmodel.RecipeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeCallback {

    interface RecipeCallback{
        fun onSuccess(recipe : List<dataRecipe>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getRecipes(callback : RecipeCallback){
        val recipeService = RecipeViewModel().apiInterface
        val call : Call<RecipeDataClass> = recipeService.getRecipe()

        call.enqueue(object : Callback<RecipeDataClass>{
            override fun onResponse(
                call: Call<RecipeDataClass>,
                response: Response<RecipeDataClass>
            ) {
                if(response.isSuccessful){
                    val recipeResponse : RecipeDataClass? = response.body()
                    // Log.d("recipeResponse", recipeResponse?.data.toString())
                    recipeResponse?.let {
                        if(it.status == 200){
                            callback.onSuccess(it.data)
                        } else {
                            callback.onError(it.status!!, it.message!!)
                        }
                    }
                } else {
                    callback.onError(response.code(),"Error at : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RecipeDataClass>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }
        })
    }
}