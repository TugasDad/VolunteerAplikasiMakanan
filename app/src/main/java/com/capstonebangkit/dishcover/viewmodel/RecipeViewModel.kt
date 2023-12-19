package com.capstonebangkit.dishcover.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.dishcover.apiInterface.RecipeAPIInterface
import com.capstonebangkit.dishcover.dataclass.RecipeDataClass
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val apiInterface = RetrofitInitial().retrofitSignUp.create(RecipeAPIInterface::class.java)
    private val _DataRecipe = MutableLiveData<List<RecipeDataClass>>()
    val DataRecipe : LiveData<List<RecipeDataClass>> = _DataRecipe

    fun getDataRecipe(){
        viewModelScope.launch {
            try {
                val getDataRecipe = apiInterface.getRecipe()
                _DataRecipe.postValue(getDataRecipe)
            } catch (E : Exception){
                Log.e("getDataRecipe() Exception", E.toString())
            }
        }
    }
}