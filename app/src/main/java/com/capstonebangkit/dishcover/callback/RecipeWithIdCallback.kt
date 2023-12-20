package com.capstonebangkit.dishcover.callback

import android.util.Log
import com.capstonebangkit.dishcover.dataclass.RecipeWithId
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.dataclass.dataRecipeWithId
import com.capstonebangkit.dishcover.viewmodel.RecipeViewModel
import com.capstonebangkit.dishcover.viewmodel.RecipeWithIdViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeWithIdCallback {

    interface RecipeCallback{
        fun onSuccess(recipeWithId: List<dataRecipeWithId>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getRecipesWithId(callback : RecipeCallback){
        val recipeWithIdService = RecipeWithIdViewModel().apiInterface
        val call : Call<RecipeWithId> = recipeWithIdService.getRecipe(RecipeWithIdViewModel().setId)

        call.enqueue(object : Callback<RecipeWithId> {
            override fun onResponse(
                call: Call<RecipeWithId>,
                response: Response<RecipeWithId>
            ) {
                if(response.isSuccessful){
                    val recipeResponse : RecipeWithId? = response.body()
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

            override fun onFailure(call: Call<RecipeWithId>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }
        })
    }
}