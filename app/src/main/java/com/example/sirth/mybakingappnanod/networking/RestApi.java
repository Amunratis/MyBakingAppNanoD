package com.example.sirth.mybakingappnanod.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestApi {

    @GET("59121517_baking/baking.json")
    Call<List<CakePOJO>> getCakes();


    @GET()
    Call<String> getStringResponse(@Url String url);
}

