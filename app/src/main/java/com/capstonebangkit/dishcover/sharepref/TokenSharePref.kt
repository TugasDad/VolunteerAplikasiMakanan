package com.capstonebangkit.dishcover.sharepref

import android.content.Context

class TokenSharePref(context : Context) {

    /*Note
    * This for example, use share preference
    * you can change use like DataStore,or more for
    * store a token */


    var tokenSharePref = context.getSharedPreferences("tokenSharePref",Context.MODE_PRIVATE)
    var shareEdit = tokenSharePref.edit()

    fun setToken(token : String?){
        shareEdit.apply {
            putString(TOKENKEY,token)
            commit()
        }
    }

    fun getToken() : String?{
        return tokenSharePref.getString(TOKENKEY,"Default Token")
    }
    
    companion object{
        val TOKENKEY = "TokenKey"
    }

}