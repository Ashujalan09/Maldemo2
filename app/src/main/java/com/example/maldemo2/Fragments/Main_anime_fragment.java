package com.example.maldemo2.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maldemo2.ModelClass.Anime;
import com.example.maldemo2.R;
import com.squareup.picasso.Picasso;

import static com.google.gson.reflect.TypeToken.get;

public class Main_anime_fragment extends Fragment {

    public static final String TAG = "Main_Anime_fragment";

    Anime mAnime;
    private MainAnimeFragmentViewModel mViewModel;
    int malId;

    public static Main_anime_fragment newInstance() {
        return new Main_anime_fragment();
    }

    ImageView image;
    TextView tv_rank, tv_score, tv_scoredBy, tv_members, tv_type, tv_synopsis;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_anime_fragment, container, false);

        tv_members = view.findViewById(R.id.main_anime_frag_members);
        tv_rank = view.findViewById(R.id.main_anime_frag_rank);
        tv_scoredBy = view.findViewById(R.id.main_anime_fragment_scored_by);
        tv_score = view.findViewById(R.id.main_anime_frag_score);
        tv_type = view.findViewById(R.id.main_anime_fragment_type);
        tv_synopsis = view.findViewById(R.id.main_anime_fragment_synopsis);
        image = view.findViewById(R.id.main_anime_fragment_image);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        malId = bundle.getInt("malID");

        Log.d(TAG, "onActivityCreated: " + bundle.getInt("malID"));

        mViewModel = new ViewModelProvider(this).get(MainAnimeFragmentViewModel.class);

        mViewModel.getAnime(malId, getContext()).observe(getViewLifecycleOwner(), new Observer<Anime>() {
            @Override
            public void onChanged(Anime anime) {
                mAnime = anime;
                tv_members.setText(String.valueOf(mAnime.getMembers()));
                tv_rank.setText(String.valueOf(mAnime.getRank()));
                tv_score.setText(String.valueOf(mAnime.getScore()));
                tv_scoredBy.setText(String.valueOf(mAnime.getScored_by()));
                tv_type.setText(mAnime.getType());
                tv_synopsis.setText(mAnime.getSynopsis());
                Picasso.get().load(mAnime.getImage_url()).fit().into(image);
            }
        });
    }
}