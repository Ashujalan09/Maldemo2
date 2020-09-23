package com.example.maldemo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;
import android.widget.Toast;

import com.example.maldemo2.MainFragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_nav);

        if (savedInstanceState==null){
            bottomNavigationView.setSelectedItemId(R.id.main_bottom_nav_feed);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.main_bottom_nav_feed:
                        Toast.makeText(MainActivity.this, "feed", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.main_bottom_nav_search:
                        Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
                        Fragment fragment;
                        fragment = new SearchFragment();
                        fragmentManager.beginTransaction().replace(R.id.main_fragment_holder, fragment).commit();
                        break;

                    case R.id.main_bottom_nav_progress:
                        Toast.makeText(MainActivity.this, "progress", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.main_bottom_nav_profile:
                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

}