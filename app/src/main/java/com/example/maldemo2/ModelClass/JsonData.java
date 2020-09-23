package com.example.maldemo2.ModelClass;

import java.util.List;

public class JsonData {

    String request_hash;

    Boolean request_cached;

    Integer request_cache_expiry;

    List<AnimeDetails> results;

    public String getRequest_hash() {
        return request_hash;
    }

    public Boolean getRequest_cached() {
        return request_cached;
    }

    public Integer getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public List<AnimeDetails> getAnime() {
        return results;
    }
}
