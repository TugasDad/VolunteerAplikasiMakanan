package com.capstonebangkit.dishcover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.apiInterface.FavoritAPIInterface
import com.capstonebangkit.dishcover.apiInterface.RecipeWithIdAPIInterface
import com.capstonebangkit.dishcover.callback.FavoriteCallback
import com.capstonebangkit.dishcover.callback.RecipeWithIdCallback
import com.capstonebangkit.dishcover.dataclass.dataFavorite
import com.capstonebangkit.dishcover.dataclass.dataRecipeWithId
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial

class FavoriteViewModel : ViewModel(){

    val apiInterface = RetrofitInitial().retrofitMyFavorit.create(FavoritAPIInterface::class.java)

    private val _dataFavorite = MutableLiveData<List<dataFavorite>>()
    val dataFavorite : LiveData<List<dataFavorite>> = _dataFavorite

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun getDataFavorite(){
       FavoriteCallback().getFavorite(object : FavoriteCallback.FavoriteCallback{
           override fun onSuccess(favorite: List<dataFavorite>) {
               _dataFavorite.value = favorite
               _status.value = 200
               _message.value = "Favorite Found"
           }

           override fun onError(statusCode: Int, errorMessage: String) {
               _status.value = statusCode
               _message.value = errorMessage
               _error.value = "Error : $statusCode - $errorMessage"
           }
       })
    }
}