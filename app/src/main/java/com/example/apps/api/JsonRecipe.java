package com.example.apps.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonRecipe {

    @GET("recipes/{id}/information")
    Call<RecipeInfo> getRecipeInfo(@Path("id") Integer number, @Query("apiKey") String api, @Query("includeNutrition") boolean includeNutrition);
}
