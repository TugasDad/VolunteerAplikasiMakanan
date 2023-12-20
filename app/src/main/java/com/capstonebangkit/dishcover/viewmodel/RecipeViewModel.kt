package com.capstonebangkit.dishcover.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.dishcover.apiInterface.RecipeAPIInterface
import com.capstonebangkit.dishcover.callback.RecipeCallback
import com.capstonebangkit.dishcover.dataclass.RecipeDataClass
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    val apiInterface = RetrofitInitial().retrofitRecipe.create(RecipeAPIInterface::class.java)

    private val _dataRecipe = MutableLiveData<List<dataRecipe>>()
    val dataRecipe : LiveData<List<dataRecipe>> = _dataRecipe

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error



    fun getDataRecipe(){
      RecipeCallback().getRecipes(object : RecipeCallback.RecipeCallback{
          override fun onSuccess(recipe: List<dataRecipe>) {
              _dataRecipe.value = recipe
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