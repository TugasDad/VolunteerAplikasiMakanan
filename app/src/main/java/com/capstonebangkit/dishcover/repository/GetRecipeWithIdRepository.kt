package com.capstonebangkit.dishcover.repository

import com.capstonebangkit.dishcover.apiInterface.RecipeWithIdAPIInterface
import com.capstonebangkit.dishcover.apirequestflow.ApiRequestFlow
import javax.inject.Inject

class GetRecipeWithIdRepository @Inject constructor(private val recipeWithId : RecipeWithIdAPIInterface) {
    fun getRecipeWithId(header : String?,id : String?) = ApiRequestFlow().apiRequestFlow {
        recipeWithId.getRecipe(header,id)
    }
}