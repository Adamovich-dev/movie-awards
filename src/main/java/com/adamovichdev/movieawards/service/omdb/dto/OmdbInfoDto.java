package com.adamovichdev.movieawards.service.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.StringJoiner;

public class OmdbInfoDto implements Serializable {

    @NotBlank
    @JsonProperty("Title")
    private String title;

    @JsonProperty("BoxOffice")
    private String boxOffice;

    @JsonProperty("imdbRating")
    private Double imdbRating;

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

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
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
