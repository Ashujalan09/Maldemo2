package com.example.maldemo2.MainFragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maldemo2.Fragments.AddProgressFragment;
import com.example.maldemo2.Fragments.Main_anime_fragment;
import com.example.maldemo2.MainAnimeActivity;
import com.example.maldemo2.ModelClass.Anime;
import com.example.maldemo2.ModelClass.AnimeDetails;
import com.example.maldemo2.R;
import com.example.maldemo2.searchRecyclerAdaptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFragment extends Fragment {

    public static final String TAG = "SearchFragment";

    List<AnimeDetails> mList = new ArrayList<>();

    Anime mAnime;

    private SearchViewModel mViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    EditText searchEditText;
    ImageButton searchButton;
    RecyclerView recyclerView;
    searchRecyclerAdaptor recyclerAdaptor;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.search_fragment, container, false);

        searchEditText = (EditText) v.findViewById(R.id.search_frag_edit_text);
        searchButton = (ImageButton) v.findViewById(R.id.Search_frag_button);

        recyclerView = v.findViewById(R.id.result_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdaptor = new searchRecyclerAdaptor();

        ItemTouchHelper.Callback itemTouchHelperCallback;
        Callback mCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.ACTION_STATE_SWIPE) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        recyclerAdaptor.setmOnClickListener(new searchRecyclerAdaptor.mOnClickListener() {
            @Override
            public void onClick(int position) {

                /*
                AnimeDetails selectedAnime = mList.get(position);

                int id = selectedAnime.getMal_id();

                Log.d(TAG, "onClick:" + id);

                Bundle bundle = new Bundle();

                bundle.putInt("malID",id);

                FragmentManager fragmentManager = getParentFragmentManager();
                Main_anime_fragment main_anime_fragment = new Main_anime_fragment();
                main_anime_fragment.setArguments(bundle);
                fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.main_fragment_holder,main_anime_fragment).addToBackStack("search").commit();

                */
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AnimeDetails animeDetails = mList.get(position);
                int malId = animeDetails.getMal_id();
                Log.d(TAG, "onSwiped: " + malId);
                FragmentManager fragmentManager = getParentFragmentManager();
                AddProgressFragment addProgressFragment = new AddProgressFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("malId",malId);
                addProgressFragment.setArguments(bundle);
                fragmentManager.beginTransaction().addToBackStack("search").replace(R.id.main_fragment_holder, addProgressFragment).commit();
            }
        }).attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(recyclerAdaptor);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        if (savedInstanceState != null) {
            Toast.makeText(getContext(), "not null", Toast.LENGTH_SHORT).show();
            recyclerAdaptor.UpdateList(mList);
            recyclerAdaptor.notifyDataSetChanged();
        }


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = searchEditText.getText().toString();
                mViewModel.getSearchList(input).observe(getViewLifecycleOwner(), new Observer<List<AnimeDetails>>() {
                    @Override
                    public void onChanged(List<AnimeDetails> animeDetails) {
                        mList = animeDetails;
                        recyclerAdaptor.UpdateList(animeDetails);
                        recyclerAdaptor.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}