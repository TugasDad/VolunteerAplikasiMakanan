package com.capstonebangkit.dishcover.apirequestflow

import com.capstonebangkit.dishcover.apiresponse.ApiResponse
import com.capstonebangkit.dishcover.errorresponse.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

class ApiRequestFlow {

    fun<T> apiRequestFlow(call : suspend() -> Response<T>) : Flow<ApiResponse<T>> = flow {
        emit(ApiResponse.Loading)

        withTimeoutOrNull(20000L){
            val response = call()

            try{
                if(response.isSuccessful){
                    response.body()?.let { data ->
                        emit(ApiResponse.Success(data))
                    }
                } else {
                    response.errorBody()?.let { error ->
                        error.close()
                        val parseError : ErrorResponse = Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                        emit(ApiResponse.Failure(parseError.message,parseError.status))
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Failure(e.message ?: e.toString(),400))
            }
        } ?: emit(ApiResponse.Failure("Timeout! Please try again", 408))
    }.flowOn(Dispatchers.IO)
}