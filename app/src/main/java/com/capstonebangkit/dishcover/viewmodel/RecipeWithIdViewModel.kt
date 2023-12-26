package com.capstonebangkit.dishcover.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.dishcover.apiInterface.RecipeAPIInterface
import com.capstonebangkit.dishcover.apiInterface.RecipeWithIdAPIInterface
import com.capstonebangkit.dishcover.apiresponse.ApiResponse
import com.capstonebangkit.dishcover.baseviewmodel.BaseViewModel
import com.capstonebangkit.dishcover.callback.RecipeCallback
import com.capstonebangkit.dishcover.callback.RecipeWithIdCallback
import com.capstonebangkit.dishcover.dataclass.RecipeWithIdDataClass
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.dataclass.dataRecipeWithId
import com.capstonebangkit.dishcover.getset.idRecipe
import com.capstonebangkit.dishcover.repository.GetRecipeWithIdRepository
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@RecipeWithIdViewModel.HiltViewModel

class RecipeWithIdViewModel @Inject constructor(
    private val getRecipeWithIdRepo : GetRecipeWithIdRepository
) : BaseViewModel(){
    annotation class HiltViewModel

    private val _getRecipeWithIdResponse = MutableLiveData<ApiResponse<RecipeWithIdDataClass>>()
    val getRecipeidResponse = _getRecipeWithIdResponse

    fun getRecipeWithId(id : String, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _getRecipeWithIdResponse,
        coroutinesErrorHandler
    ){
        getRecipeWithIdRepo.getRecipeWithId("Header at here", idRecipe().RecipeId)
    }

}

/* var setId = "22"
       get() = field
       set(value) {
           field = value
       }

   val apiInterface = {context : Context ->
       RetrofitInitial().retrofitRecipeWithId(setId,context).create(RecipeWithIdAPIInterface::class.java)
   }

   private val _dataRecipeWithId = MutableLiveData<List<dataRecipeWithId>>()
   val dataRecipeWithId : LiveData<List<dataRecipeWithId>> = _dataRecipeWithId

   private val _status = MutableLiveData<Int>()
   val status : LiveData<Int> get() = _status

   private val _message = MutableLiveData<String>()
   val message : LiveData<String> get() = _message

   private val _error = MutableLiveData<String>()
   val error : LiveData<String> get() = _error


   fun getDataRecipe(context : Context){
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
       },context)
   }*/


