package com.example.maldemo2.MainFragments;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.maldemo2.MainRepository;
import com.example.maldemo2.ModelClass.AnimeDetails;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    public static final String TAG = "SearchViewModel";

    Context context;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    MainRepository mainRepository = new MainRepository();

    LiveData<List<AnimeDetails>> getSearchList(String name){
        return mainRepository.getSearchAnimeList(name);
    }

}