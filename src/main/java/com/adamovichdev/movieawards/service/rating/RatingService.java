package com.adamovichdev.movieawards.service.rating;

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

    public RatingDto getRatingByUserIdAndTitleId (Integer userId, Integer titleId) {
        Integer grage = repository.getGradeByUserIdAndTitleId(userId, titleId);

        RatingDto response = new RatingDto();
        response.setGrade(grage);
        response.setTitleId(titleId);
        response.setUserId(userId);

        return response;
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
