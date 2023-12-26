package com.capstonebangkit.dishcover.callback

import android.content.Context
import android.util.Log
import com.capstonebangkit.dishcover.dataclass.RecipeWithIdDataClass
import com.capstonebangkit.dishcover.dataclass.dataRecipeWithId
import com.capstonebangkit.dishcover.sharepref.TokenSharePref
import com.capstonebangkit.dishcover.viewmodel.RecipeWithIdViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeWithIdCallback {

   /* interface RecipeCallback{
        fun onSuccess(recipeWithId: List<dataRecipeWithId>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getRecipesWithId(callback : RecipeCallback, context : Context){
        val recipeWithIdService = RecipeWithIdViewModel().apiInterface(context)
        val call : Call<RecipeWithIdDataClass> = recipeWithIdService.getRecipe(RecipeWithIdViewModel().setId)

        call.enqueue(object : Callback<RecipeWithIdDataClass> {
            override fun onResponse(
                call: Call<RecipeWithIdDataClass>,
                response: Response<RecipeWithIdDataClass>
            ) {
                if(response.isSuccessful){
                    val recipeResponse : RecipeWithIdDataClass? = response.body()
                    Log.d("recipeResponse", recipeResponse?.data.toString())
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

            override fun onFailure(call: Call<RecipeWithIdDataClass>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }
        })
    }*/
}