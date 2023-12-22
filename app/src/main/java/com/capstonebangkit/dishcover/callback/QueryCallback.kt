package com.capstonebangkit.dishcover.callback

import android.util.Log
import com.capstonebangkit.dishcover.dataclass.QueryData
import com.capstonebangkit.dishcover.dataclass.QueryDataClass
import com.capstonebangkit.dishcover.viewmodel.QueryViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QueryCallback {

    interface QueryCallback{
        fun onSuccess(query: List<QueryData>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getQueryMenu(callback : QueryCallback){
        val queryService = QueryViewModel().apiInterface
        val call : Call<QueryDataClass> = queryService.getMenuByQuery(QueryViewModel().menu)

        call.enqueue(object : Callback<QueryDataClass>{
            override fun onResponse(
                call: Call<QueryDataClass>,
                response: Response<QueryDataClass>
            ) {
               if(response.isSuccessful){
                   val queryResponse : QueryDataClass? = response.body()
                   Log.d("Query ${QueryViewModel().menu}", queryResponse?.data.toString())

                   queryResponse?.let {
                       if(it.status == 200){
                           callback.onSuccess(it.data)
                       } else {
                           callback.onError(it.status!!, it.message!!)
                       }
                   }
               } else {
                   callback.onError(response.code(),"Error at : ${response.code()}")
               }
            }

            override fun onFailure(call: Call<QueryDataClass>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }

        })
    }


}