package com.capstonebangkit.dishcover.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.dishcover.apiInterface.LoginAPIInterface
import com.capstonebangkit.dishcover.callback.LoginCallback
import com.capstonebangkit.dishcover.dataclass.LoginDataClass
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import kotlinx.coroutines.launch

class LoginViewModel(private val loginCallback : LoginCallback) : ViewModel() {

    // private val apiInterface = RetrofitInitial().retrofitLogin.create(LoginAPIInterface::class.java)

    private val _DataLogin = MutableLiveData<Result<String>>()
    val DataLogin : LiveData<Result<String>> = _DataLogin

    fun loginDataUser(username : String?,password : String?){
        loginCallback.login(username,password){result ->
            _DataLogin.value = result
        }
    }


}