package com.example.maldemo2;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maldemo2.ModelClass.AnimeDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class searchRecyclerAdaptor extends RecyclerView.Adapter<searchRecyclerAdaptor.searchViewHolder> {

    List<AnimeDetails> searchList = new ArrayList<>();
    mOnClickListener mOnClickListener;

    public void UpdateList(List<AnimeDetails> newList) {
        searchList = newList;
    }

    public void setmOnClickListener(mOnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public interface mOnClickListener {
        void onClick(int position);
    }

    class searchViewHolder extends RecyclerView.ViewHolder {

        TextView name, type, episodes, score, rated;
        ImageView image;
        ConstraintLayout layout;

        public searchViewHolder(@NonNull View itemView, final mOnClickListener listener) {

            super(itemView);

            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onClick(position);
                    }
                }
            });
            name = itemView.findViewById(R.id.rv_name);
            episodes = itemView.findViewById(R.id.rv_displayEpisodes);
            type = itemView.findViewById(R.id.displayType);
            score = itemView.findViewById(R.id.rv_displayScore);
            rated = itemView.findViewById(R.id.rv_displayRating);
            image = itemView.findViewById(R.id.rv_imageView);
            layout = itemView.findViewById(R.id.rv_layout);
        }
    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_layout, parent, false);
        return new searchViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {
        AnimeDetails CurrentDetails = searchList.get(position);
        Picasso.get().load(CurrentDetails.getImage_url()).into(holder.image);
        holder.name.setText(CurrentDetails.getTitle());
        holder.episodes.setText("Ep:" + String.valueOf(CurrentDetails.getEpisodes()));
        holder.type.setText(CurrentDetails.getType());
        holder.rated.setText(CurrentDetails.getRated());
        holder.score.setText(String.valueOf(CurrentDetails.getScore()));
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

}