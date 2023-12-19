package com.capstonebangkit.dishcover.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.dishcover.apiInterface.SignUpAPIInterface
import com.capstonebangkit.dishcover.dataclass.SignUpDataClass
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val apiInterface = RetrofitInitial().retrofitSignUp.create(SignUpAPIInterface::class.java)
    private val _DataSignUp = MutableLiveData<List<SignUpDataClass>>()
    val DataSignUp : LiveData<List<SignUpDataClass>> = _DataSignUp

    fun SignUpDataUser(name : String?, username : String?, email : String?,password : String?){
        viewModelScope.launch {
            try {
                val callData = apiInterface.signUpDataUser(SignUpDataClass(name,username, email, password))
            } catch (E : Exception){
                Log.e("SignUpDataUser() Exception", E.toString())
            }
        }
    }
}