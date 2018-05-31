package com.example.sirth.mybakingappnanod.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("59121517_baking/baking.json")
    Call<List<CakePOJO>> getCakes();
}