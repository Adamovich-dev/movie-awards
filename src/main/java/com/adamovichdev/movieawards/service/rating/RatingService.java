package com.adamovichdev.movieawards.service.rating;

import com.adamovichdev.movieawards.dao.entity.RatingEntity;
import com.adamovichdev.movieawards.dao.entity.projection.RatingViewProjection;
import com.adamovichdev.movieawards.web.api.rating.RatingDto;

public interface RatingService {

    /**
     * Search User's Movie rating by MovieAwardEntity id and UserEntity id.
     * Return view if rating present
     *
     * @param userId User entity id
     * @param titleId MovieAward id
     * @return rating View
     *
     * @see RatingViewProjection
     * @see RatingEntity
     */
    RatingViewProjection getRatingByUserIdAndTitleId (Long userId, Long titleId);

    void saveRating(RatingDto rating);

    void updateRating(RatingDto rating);
}
