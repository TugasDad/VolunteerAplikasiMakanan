package com.capstonebangkit.dishcover.dataclass

import com.google.gson.annotations.SerializedName

data class FavoriteDataClass(
    var status : Int?,
    var message : String?,
    var data : List<dataFavorite>
)

data class dataFavorite(
    @SerializedName("id") var id : String?,
    @SerializedName("user_id") var userId : String?,
    var recipe_id : dataFavoriteId
)

data class dataFavoriteId(
    @SerializedName("id") var id : String?,
    @SerializedName("name") var name : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("ingredients") var ingredients : String?,
    @SerializedName("step") var step : String?,
    @SerializedName("urlimage") var urlimage : String?,
)

