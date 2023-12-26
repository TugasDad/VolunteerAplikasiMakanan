package com.capstonebangkit.dishcover.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import  androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.apiInterface.LoginAPIInterface
import com.capstonebangkit.dishcover.callback.LoginCallback
import com.capstonebangkit.dishcover.dataclass.LoginDataClass
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial
import com.capstonebangkit.dishcover.sharepref.TokenSharePref

class LoginViewModel : ViewModel() {

    var username = "endangkus"
        get() = field
        set(value) {
            field = value
        }

    var password = "123456"
        get() = field
        set(value) {
            field = value
        }

    val apiInterface = RetrofitInitial().retrofitLogin.create(LoginAPIInterface::class.java)

    private val _dataLoginToken = MutableLiveData<String?>()
    val DataLogin : LiveData<String?> = _dataLoginToken

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    // post a login data
    fun loginDataUser(context : Context){
        LoginCallback().getLoginResponse(object : LoginCallback.LoginCallback{
            override fun onSuccess(Response: String?) {
                _dataLoginToken.value = Response
                _message.value = "Get Token Succesfull"
            }

            override fun onError(statusCode: Int, errorMessage: String) {
                _status.value = statusCode
                _message.value = errorMessage
                _error.value = "Error : $statusCode - $errorMessage"
            }
        }, context)
    }
}