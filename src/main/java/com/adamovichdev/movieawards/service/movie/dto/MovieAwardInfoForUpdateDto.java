package com.adamovichdev.movieawards.service.movie.dto;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with data for update MovieAwardEntity
 */
public class MovieAwardInfoForUpdateDto implements Serializable {

    /**
     * MovieAwardEntity id
     */
    private Long movieAwardId;

    /**
     * Movie box office
     */
    private Long boxOffice;

    /**
     * Omdb rating
     */
    private Double imdbRating;

    public Long getMovieAwardId() {
        return movieAwardId;
    }

    public void setMovieAwardId(Long movieAwardId) {
        this.movieAwardId = movieAwardId;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", MovieAwardInfoForUpdateDto.class.getName() + "{", "}")
                .add("movieAwardId = '" + movieAwardId + "'")
                .add("boxOffice = '" + boxOffice + "'")
                .add("imdbRating = '" + imdbRating + "'")
                .toString();
    }
}
