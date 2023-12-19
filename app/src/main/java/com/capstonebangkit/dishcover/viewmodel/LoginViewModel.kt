package com.capstonebangkit.dishcover.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.dishcover.apiInterface.LoginAPIInterface
import com.capstonebangkit.dishcover.dataclass.LoginDataClass
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val apiInterface = RetrofitInitial().retrofitLogin.create(LoginAPIInterface::class.java)
    private val _DataLogin = MutableLiveData<List<LoginDataClass>>()
    val DataLogin : LiveData<List<LoginDataClass>> = _DataLogin

    fun loginDataUser(username : String?,password : String?){
        viewModelScope.launch {
            try {
                val callData = apiInterface.LoginDataUser(LoginDataClass(username, password))
            } catch (E : Exception){
                Log.e("LoginDataUser() Exception", E.toString())
            }
        }
    }


}