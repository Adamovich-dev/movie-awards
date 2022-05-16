package com.adamovichdev.movieawards.web.api.rating;

import java.io.Serializable;
import java.util.StringJoiner;

public class RatingDto implements Serializable {

    private Integer grade;
    private Integer userId;
    private Integer titleId;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
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
