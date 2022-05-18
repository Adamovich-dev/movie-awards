package com.adamovichdev.movieawards.service.facade;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.service.movie.MovieAwardService;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.OmdbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.adamovichdev.movieawards.Util.LoggerUtil.logException;
import static com.adamovichdev.movieawards.Util.LoggerUtil.logRequest;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
public class UpdateMovieAwardFacade {

    private static final Logger logger = LoggerFactory.getLogger(UpdateMovieAwardFacade.class);

    private final MovieAwardService movieAwardService;
    private final OmdbService omdbService;

    @Autowired
    public UpdateMovieAwardFacade(MovieAwardService movieAwardService, OmdbService omdbService) {
        this.movieAwardService = movieAwardService;
        this.omdbService = omdbService;
    }

    public void updateMovieAwardsFromImdb() {
        final String methodName = "updateMovieAwardsFromImdb";
        logRequest(logger, methodName, "");

        final List<MovieAwardOmdbProjection> movieAwardProjectionList = movieAwardService.getAllMovieAwardIdAndTitle();
        if (isNotEmpty(movieAwardProjectionList)) {
            final Map<Long, String> movieAwardIdAndNomineeMap = getMovieIdAndTitleMap(movieAwardProjectionList);
            final List<MovieAwardInfoForUpdateDto> infoForUpdateList = omdbService.getOmdbDataForUpdateMovieAward(movieAwardIdAndNomineeMap);
            if (isNotEmpty(infoForUpdateList)) {
                infoForUpdateList.parallelStream()
                        .filter(Objects::nonNull)
                        .forEach(infoForUpdate -> {
                            try {
                                movieAwardService.updateMovieAwardData(infoForUpdate);
                            } catch (Exception e) {
                                //ignoring single fail update from list
                                logException(logger, methodName, e);
                            }
                        });
            }
        }
    }

    private Map<Long, String> getMovieIdAndTitleMap(List<MovieAwardOmdbProjection> movieAwardProjectionList) {
        return movieAwardProjectionList.parallelStream()
                .collect(Collectors.toMap(MovieAwardOmdbProjection::getId, MovieAwardOmdbProjection::getTitle));
    }
}
