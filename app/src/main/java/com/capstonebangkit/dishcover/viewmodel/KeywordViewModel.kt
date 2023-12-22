package com.capstonebangkit.dishcover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.apiInterface.KeywordAPIInterface
import com.capstonebangkit.dishcover.apiInterface.QueryAPiInterface
import com.capstonebangkit.dishcover.callback.KeywordCallback
import com.capstonebangkit.dishcover.dataclass.KeywordData
import com.capstonebangkit.dishcover.dataclass.QueryData
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial

class KeywordViewModel : ViewModel(){

    // in here we gonna make concatnation with , (comma)
    var keyword = "ayam,sayur"
        get() = field
        set(value) {
            field = value
        }

    val apiInterface = RetrofitInitial().retrofitKeyword(keyword).create(KeywordAPIInterface::class.java)

    private val _keyworddata = MutableLiveData<List<KeywordData>>()
    val keyworddata : LiveData<List<KeywordData>> = _keyworddata

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun getKeywordResult(){
        KeywordCallback().getKeywordDataMenu(object  : KeywordCallback.KeywordCallback{
            override fun onSuccess(keyword: List<KeywordData>) {
                _keyworddata.value = keyword
                _status.value = 200
                _message.value = "Data Menu Keyword Found"
            }

            override fun onError(statusCode: Int, errorMessage: String) {
                _status.value = statusCode
                _message.value = errorMessage
                _error.value = "Error : $statusCode - $errorMessage"
            }

        })
    }

}