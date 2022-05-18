package com.adamovichdev.movieawards.service.rating;

import com.adamovichdev.movieawards.dao.entity.projection.RatingViewProjection;
import com.adamovichdev.movieawards.dao.repository.RatingRepository;
import com.adamovichdev.movieawards.web.api.rating.RatingDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService {

    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public RatingViewProjection getRatingByUserIdAndTitleId (Integer userId, Integer titleId) {
        return repository.getGradeByUserIdAndTitleId(userId, titleId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRating(RatingDto rating) {
        repository.insertTitleRatingFromUser(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRating(RatingDto rating) {
        repository.updateGradeByUserIdAndTitleId(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }
}
