package com.example.apps.apimaps;

import com.example.apps.api.RecipeInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonToCoordinates {

    @GET("/maps/api/place/nearbysearch/json")
    Call<PlaceCoordinates> getPlace(@Query("location") String location, @Query("radius") float radius, @Query("type") String type, @Query("key") String key );

}
