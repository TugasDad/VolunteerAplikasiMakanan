package com.capstonebangkit.dishcover.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.apiInterface.FavoritAPIInterface
import com.capstonebangkit.dishcover.callback.FavoritePostCallback
import com.capstonebangkit.dishcover.dataclass.dataRecipe
import com.capstonebangkit.dishcover.dataclass.responsePostDataClass
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial

class FavoritePostViewModel : ViewModel(){
    var recipe_id = "22"
        get() = field
        set(value) {
            field = value
        }

    val apiInterface = {context : Context ->
        RetrofitInitial().retrofitFavoritePost(recipe_id,context).create(FavoritAPIInterface::class.java)
    }

    private val _dataFavoritePost = MutableLiveData<List<responsePostDataClass>>()
    val dataFavoritePost : LiveData<List<responsePostDataClass>> = _dataFavoritePost

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun postFavorite(context : Context){
        FavoritePostCallback().postFavorite(object : FavoritePostCallback.FavoritePost{
            override fun onSuccess(favoritePost: List<responsePostDataClass>) {
                _dataFavoritePost.value = favoritePost
                _status.value = 200
                _message.value = "Post Favorite Success"
            }

            override fun onError(statusCode: Int, errorMessage: String) {
                _status.value = statusCode
                _message.value = errorMessage
                _error.value = "Error : $statusCode - $errorMessage"
            }

        },context)
    }
}