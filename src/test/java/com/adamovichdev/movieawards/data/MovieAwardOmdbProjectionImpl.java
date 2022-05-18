package com.adamovichdev.movieawards.data;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;

public class MovieAwardOmdbProjectionImpl implements MovieAwardOmdbProjection {

    private final Long id;
    private final String title;

    public MovieAwardOmdbProjectionImpl(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public MovieAwardOmdbProjectionImpl() {
        this.id = null;
        this.title = null;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
