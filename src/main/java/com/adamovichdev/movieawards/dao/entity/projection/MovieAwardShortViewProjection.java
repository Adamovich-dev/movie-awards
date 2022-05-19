package com.adamovichdev.movieawards.dao.entity.projection;

public interface MovieAwardShortViewProjection {

    Long getId();

    String getTitle();

    String getOskarYear();

    Double getUsersRating();

    Integer getNumberOfUsersRating();

    Double getImdbRating();

    String getIsWon();

    Long getBoxOffice();
}
