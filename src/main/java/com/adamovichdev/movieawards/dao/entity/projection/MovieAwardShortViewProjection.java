package com.adamovichdev.movieawards.dao.entity.projection;

public interface MovieAwardShortViewProjection {

    Long getId();

    String getTitle();

    String getOskarYear();

//    Double getAvgGrade();

    String getIsWon();

//    Integer getNumberOfRatings();

    Long getBoxOffice();
}
