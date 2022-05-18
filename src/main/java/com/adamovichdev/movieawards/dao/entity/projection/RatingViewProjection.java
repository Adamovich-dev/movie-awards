package com.adamovichdev.movieawards.dao.entity.projection;

public interface RatingViewProjection {

    Long getUserId();

    Long getMovieAwardId();

    Integer getGrade();
}
