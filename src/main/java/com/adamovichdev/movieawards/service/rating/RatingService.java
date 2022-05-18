package com.adamovichdev.movieawards.service.rating;

import com.adamovichdev.movieawards.dao.entity.projection.RatingViewProjection;
import com.adamovichdev.movieawards.dao.repository.RatingRepository;
import com.adamovichdev.movieawards.web.api.rating.RatingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.adamovichdev.movieawards.Util.LoggerUtil.logRequest;

@Service
public class RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public RatingViewProjection getRatingByUserIdAndTitleId (Long userId, Long titleId) {
        final String methodName = "getRatingByUserIdAndTitleId";
        logRequest(logger, methodName, userId, titleId);

        return repository.getRatingViewByUserIdAndTitleId(userId, titleId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRating(RatingDto rating) {
        final String methodName = "saveRating";
        logRequest(logger, methodName, rating);

        repository.insertTitleRatingFromUser(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRating(RatingDto rating) {
        final String methodName = "updateRating";
        logRequest(logger, methodName, rating);

        repository.updateGradeByUserIdAndTitleId(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }
}
