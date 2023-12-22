package com.capstonebangkit.dishcover.dataclass

import com.google.gson.annotations.SerializedName

data class KeywordDataClass(
    val status : Int?,
    val message : String?,
    val data : List<KeywordData>
)

data class KeywordData(
    @SerializedName("id") var id : String?,
    @SerializedName("name") var name : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("urlimage") var urlImage : String?
)
