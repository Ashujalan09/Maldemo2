package com.example.maldemo2.ModelClass;

import java.util.List;

public class Anime {
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAired(com.example.maldemo2.ModelClass.aired aired) {
        this.aired = aired;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setScored_by(int scored_by) {
        this.scored_by = scored_by;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    String title;
    int mal_id;
    String image_url;
    String type;
    String source;
    int rank;
    int popularity;
    int members;
    int favorites;
    String status;
    aired aired;
    float score;
    int scored_by;
    int episodes;
    int rating;
    String synopsis;
    String background;
    String premiered;
    List<Genre> genres;


    public String getTitle() {
        return title;
    }

    public int getMal_id() {
        return mal_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public int getRank() {
        return rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getMembers() {
        return members;
    }

    public int getFavorites() {
        return favorites;
    }

    public String getStatus() {
        return status;
    }

    public com.example.maldemo2.ModelClass.aired getAired() {
        return aired;
    }

    public float getScore() {
        return score;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getRating() {
        return rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getScored_by() {
        return scored_by;
    }

    public String getBackground() {
        return background;
    }

    public String getPremiered() {
        return premiered;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
