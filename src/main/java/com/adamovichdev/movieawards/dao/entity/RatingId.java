package com.adamovichdev.movieawards.dao.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {

    @ManyToOne
    @JoinColumn (name="movie_award_id")
    private MovieAwardEntity movieAwardId;

    @ManyToOne
    @JoinColumn (name="user_id")
    private UserEntity userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId ratingId = (RatingId) o;
        return movieAwardId == ratingId.movieAwardId && userId == ratingId.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieAwardId, userId);
    }

    public MovieAwardEntity getMovieAwardId() {
        return movieAwardId;
    }

    public void setMovieAwardId(MovieAwardEntity movieAwardId) {
        this.movieAwardId = movieAwardId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
