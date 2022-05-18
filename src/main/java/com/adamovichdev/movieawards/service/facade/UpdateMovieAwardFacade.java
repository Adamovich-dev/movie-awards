package com.adamovichdev.movieawards.service.facade;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.service.movie.MovieAwardService;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
public class UpdateMovieAwardFacade {

    private final MovieAwardService movieAwardService;
    private final OmdbService omdbService;

    @Autowired
    public UpdateMovieAwardFacade(MovieAwardService movieAwardService, OmdbService omdbService) {
        this.movieAwardService = movieAwardService;
        this.omdbService = omdbService;
    }

    public void updateMovieAwardsFromImdb() {
        final List<MovieAwardOmdbProjection> movieAwardProjectionList = movieAwardService.getAllMovieAwardIdAndTitle();
        if (isNotEmpty(movieAwardProjectionList)) {
            final Map<Long, String> movieAwardIdAndNomineeMap = getMovieIdAndTitleMap(movieAwardProjectionList);

            List<MovieAwardInfoForUpdateDto> infoForUpdateList = omdbService.getOmdbDataForUpdateMovieAward(movieAwardIdAndNomineeMap);
            if (isNotEmpty(infoForUpdateList)) {
                infoForUpdateList.parallelStream()
                        .filter(Objects::nonNull)
                        .forEach(movieAwardService::updateMovieAwardData);
            }
        }

        System.out.println("!!!!!!!!!!!!!ВСЕ!!!!!!!!!!!!!!!");
    }

    private Map<Long, String> getMovieIdAndTitleMap(List<MovieAwardOmdbProjection> movieAwardProjectionList) {
        return movieAwardProjectionList.parallelStream()
                .collect(Collectors.toMap(MovieAwardOmdbProjection::getId, MovieAwardOmdbProjection::getTitle));
    }
}
