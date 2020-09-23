package com.example.maldemo2;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.maldemo2.ModelClass.Anime;
import com.example.maldemo2.ModelClass.AnimeDetails;
import com.example.maldemo2.ModelClass.JsonData;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainRepository {

    public static final int LIMIT_NUM = 20;

    public static final String TAG = "MainRepository";

    MutableLiveData<List<AnimeDetails>> SearchAnimeList = new MutableLiveData<>();
    MutableLiveData<Anime> AnimeData = new MutableLiveData<>();
    Anime nAnime;


    public LiveData<List<AnimeDetails>> getSearchAnimeList(String inputText) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitMalInterface malInterface = retrofit.create(RetrofitMalInterface.class);

        Call<JsonData> callSearch = malInterface.getSearchList(inputText, LIMIT_NUM);

        callSearch.enqueue(new Callback<JsonData>() {
            @Override
            public void onResponse(Call<JsonData> call, Response<JsonData> response) {
                if (response.isSuccessful()) {
                    JsonData data = response.body();
                    assert data != null;
                    List<AnimeDetails> SearchList = data.getAnime();
                    SearchAnimeList.postValue(SearchList);
                    Log.i("search", "success");
                }
            }

            @Override
            public void onFailure(Call<JsonData> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
        return SearchAnimeList;
    }

    public LiveData<Anime> getAnime(int id, Context context) {

        String baseUrl = "https://api.jikan.moe/v3/anime/";
        String malId = String.valueOf(id);
        String url = baseUrl + malId;
        RequestQueue mQueue = Volley.newRequestQueue(context);
        Log.d(TAG, "url" + url);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, "inside onResponse");
                    nAnime.setTitle(response.getString("title"));
                    nAnime.setMembers(response.getInt("members"));
                    nAnime.setEpisodes(response.getInt("episodes"));
                    nAnime.setRank(response.getInt("rank"));
                    nAnime.setScore(response.getLong("score"));
                    Log.d(TAG, "onResponse: "+ response.getLong("score"));
                    nAnime.setImage_url(response.getString("image_url"));
                    nAnime.setScored_by(response.getInt("scored_by"));
                    nAnime.setType(response.getString("type"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: getAnime error");
            }
        });

        mQueue.add(objectRequest);

        AnimeData.setValue(nAnime);

        return AnimeData;
    }
}
