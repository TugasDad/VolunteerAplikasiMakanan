package com.capstonebangkit.dishcover.dataclass

import com.google.gson.annotations.SerializedName

data class RecipeStatus(
    var status : String?,
    var message : String?,
)

data class dataRecipe(
    @SerializedName("id") var id : String?,
    @SerializedName("name") var name : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("urlimage") var urlImage : String?
)

data class RecipeDataClass(
    val recipeStatus : RecipeStatus,
    val dataRecipe : List<dataRecipe>
)
