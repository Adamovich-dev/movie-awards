package com.adamovichdev.movieawards.web.api.rating;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with rating data
 */
public class RatingDto implements Serializable {

    /**
     * User's movie rating
     */
    private Integer grade;

    /**
     * UserEntity id
     */
    private Long userId;

    /**
     * MovieAwardEntity id
     */
    private Long titleId;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", RatingDto.class.getName() + "{", "}")
                .add("grade = '" + grade + "'")
                .add("userId = '" + userId + "'")
                .add("titleId = '" + titleId + "'")
                .toString();
    }
}
