package com.example.maldemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.maldemo2.Fragments.Main_anime_fragment;

public class MainAnimeActivity extends AppCompatActivity {

    public static final int DEFAULT_ANIME_CODE = 999999999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_anime);
        Intent intent = getIntent();

        int malId = intent.getIntExtra("mal_id",DEFAULT_ANIME_CODE);

        Bundle bundle = new Bundle();
        bundle.putInt("malId",malId);

        Main_anime_fragment main_anime_fragment = new Main_anime_fragment();
        main_anime_fragment.setArguments(bundle);

        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.anime_activity_fragment_holder,main_anime_fragment).commit();
    }
}