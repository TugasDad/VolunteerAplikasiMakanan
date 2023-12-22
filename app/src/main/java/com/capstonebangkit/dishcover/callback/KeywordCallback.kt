package com.capstonebangkit.dishcover.callback

import android.util.Log
import com.capstonebangkit.dishcover.dataclass.KeywordData
import com.capstonebangkit.dishcover.dataclass.KeywordDataClass
import com.capstonebangkit.dishcover.dataclass.QueryData
import com.capstonebangkit.dishcover.viewmodel.KeywordViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeywordCallback {

    interface KeywordCallback{
        fun onSuccess(keyword: List<KeywordData>)
        fun onError(statusCode : Int, errorMessage : String)
    }

    fun getKeywordDataMenu(callback : KeywordCallback){
        val keywordService = KeywordViewModel().apiInterface
        val call : Call<KeywordDataClass> = keywordService.getMenuByKeyword(KeywordViewModel().keyword)
        println(KeywordViewModel().keyword)

        call.enqueue(object : Callback<KeywordDataClass>{
            override fun onResponse(
                call: Call<KeywordDataClass>,
                response: Response<KeywordDataClass>
            ) {
                if(response.isSuccessful){
                    val keywordResponse : KeywordDataClass? = response.body()
                    Log.d("Keyword ${KeywordViewModel().keyword}", keywordResponse?.data.toString())

                    keywordResponse?.let {
                        if(it.status == 200){
                            callback.onSuccess(it.data)
                        } else {
                            callback.onError(it.status!!,it.message!!)
                        }
                    }
                } else {
                    callback.onError(response.code(), "Error at : ${response.code()}")
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<KeywordDataClass>, t: Throwable) {
                callback.onError(0,"Network request fail : ${t.message}")
            }

        })
    }
}