package com.example.maldemo2.Fragments;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.maldemo2.MainRepository;
import com.example.maldemo2.ModelClass.Anime;

import java.util.List;

public class MainAnimeFragmentViewModel extends AndroidViewModel {
    MainRepository mainRepository = new MainRepository();

    public MainAnimeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<Anime> getAnime(int id, Context context){
        return mainRepository.getAnime(id, context);
    }

}