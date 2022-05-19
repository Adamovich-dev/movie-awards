package com.adamovichdev.movieawards.service.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with data from OMDB Service
 */
public class OmdbInfoDto implements Serializable {

    /**
     * Movie title
     */
    @NotBlank
    @JsonProperty("Title")
    private String title;

    /**
     * Movie box office
     */
    @JsonProperty("BoxOffice")
    private String boxOffice;

    /**
     * Movie Imdb rating
     */
    @JsonProperty("imdbRating")
    private String imdbRating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", OmdbInfoDto.class.getName() + "{", "}")
                .add("title = '" + title + "'")
                .add("boxOffice = '" + boxOffice + "'")
                .add("imdbRating = '" + imdbRating + "'")
                .toString();
    }
}
