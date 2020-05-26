package com.example.apps.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonToObject {

    @GET("recipes/findByIngredients")
    Call<List<Recipe>> getRecipes(@Query("apiKey") String api, @Query("ingredients") String[] ingredients, @Query("number") Integer number,
                                  @Query("limitLicense") boolean limitLicense, @Query("ranking") Integer ranking, @Query("ignorePantry") boolean ignorePantry);


}
