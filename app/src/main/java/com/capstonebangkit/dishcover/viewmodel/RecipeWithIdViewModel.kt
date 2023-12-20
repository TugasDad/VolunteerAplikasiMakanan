package com.capstonebangkit.dishcover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.apiInterface.RecipeAPIInterface
import com.capstonebangkit.dishcover.apiInterface.RecipeWithIdAPIInterface
import com.capstonebangkit.dishcover.callback.RecipeCallback
import com.capstonebangkit.dishcover.callback.RecipeWithIdCallback
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.dataclass.dataRecipeWithId
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial

class RecipeWithIdViewModel : ViewModel(){
    var setId = "22"
        get() = field
        set(value) {
            field = value
        }

    val apiInterface = RetrofitInitial().retrofitRecipeWithId(setId).create(RecipeWithIdAPIInterface::class.java)

    private val _dataRecipeWithId = MutableLiveData<List<dataRecipeWithId>>()
    val dataRecipeWithId : LiveData<List<dataRecipeWithId>> = _dataRecipeWithId

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error



    fun getDataRecipe(){
        RecipeWithIdCallback().getRecipesWithId(object : RecipeWithIdCallback.RecipeCallback{
            override fun onSuccess(recipe: List<dataRecipeWithId>) {
                _dataRecipeWithId.value = recipe
                _status.value = 200
                _message.value = "Recipe Found"
            }

            override fun onError(statusCode: Int, errorMessage: String) {
                _status.value = statusCode
                _message.value = errorMessage
                _error.value = "Error : $statusCode - $errorMessage"
            }
        })
    }
}