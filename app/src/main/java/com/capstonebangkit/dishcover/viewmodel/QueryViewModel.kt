package com.capstonebangkit.dishcover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstonebangkit.dishcover.apiInterface.QueryAPiInterface
import com.capstonebangkit.dishcover.callback.QueryCallback
import com.capstonebangkit.dishcover.dataclass.QueryData
import com.capstonebangkit.dishcover.retrofitInit.RetrofitInitial

class QueryViewModel : ViewModel(){

    var menu = "ayam"
        get() = field
        set(value) {
            field = value
        }

    val apiInterface = RetrofitInitial().retrofitQuery(menu).create(QueryAPiInterface::class.java)

    private val _querydata = MutableLiveData<List<QueryData>>()
    val queryData : LiveData<List<QueryData>> = _querydata

    private val _status = MutableLiveData<Int>()
    val status : LiveData<Int> get() = _status

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun getQueryResult(){
        QueryCallback().getQueryMenu(object : QueryCallback.QueryCallback{
            override fun onSuccess(query: List<QueryData>) {
                _querydata.value = query
                _status.value = 200
                _message.value = "Query Found"
            }

            override fun onError(statusCode: Int, errorMessage: String) {
                _status.value = statusCode
                _message.value = errorMessage
                _error.value = "Error : $statusCode - $errorMessage"
            }

        })
    }


}