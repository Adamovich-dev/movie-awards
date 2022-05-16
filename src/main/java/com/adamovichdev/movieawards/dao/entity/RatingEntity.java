package com.adamovichdev.movieawards.dao.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USER_MOVIE_RATING")
public class RatingEntity implements Serializable {

    @EmbeddedId
    private RatingId id;

    @Column(name = "grade")
    @Size(min = 0, max = 10, message = "grade must be from 0 to 10")
    private Integer grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingEntity that = (RatingEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
