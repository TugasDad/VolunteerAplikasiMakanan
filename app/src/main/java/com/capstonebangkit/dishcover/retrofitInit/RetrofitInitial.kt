package com.capstonebangkit.dishcover.retrofitInit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitial {

    var retrofitSignUp = Retrofit.Builder()
        .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/user/signup/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var retrofitLogin = Retrofit.Builder()
        .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/user/login/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var retrofitRecipe = Retrofit.Builder()
        .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/recipe/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var retrofitRecipeWithId = { id : String? ->
        Retrofit.Builder()
            .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/recipe/${id}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var retrofitMyFavorit = Retrofit.Builder()
        .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/myfavourite/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var retrofitQuery = { query : String? ->
        Retrofit.Builder()
            .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/recipe/?query=${query}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var retrofitKeyword = {keyword : String? ->
        Retrofit.Builder()
            .baseUrl("https://backend-dot-dishcoverapi.et.r.appspot.com/api/recipe/?keywords=${keyword}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}