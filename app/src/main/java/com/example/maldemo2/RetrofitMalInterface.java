package com.example.maldemo2;

import com.example.maldemo2.ModelClass.Anime;
import com.example.maldemo2.ModelClass.JsonData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitMalInterface {

    @GET("search/anime")
    Call<JsonData> getSearchList(@Query("q") String name, @Query("limit") int num);

    @GET("anime/{id}")
    Call<Anime> getAnime(@Path("id") int id);

}
