package com.capstonebangkit.dishcover.dataclass

import com.google.gson.annotations.SerializedName

data class QueryDataClass(
    var status : Int?,
    var message : String?,
    var data : List<QueryData>
)

data class QueryData(
    @SerializedName("id") var id : String?,
    @SerializedName("name") var name : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("urlimage") var urlImage : String?
)
