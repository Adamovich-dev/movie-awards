package com.adamovichdev.movieawards.service.omdb;

import com.adamovichdev.movieawards.dao.entity.MovieAwardEntity;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;

import java.util.List;
import java.util.Map;

public interface OmdbService {

    /**
     * Sends async requests to OMDB API, gets information about movie by title and return data
     * for update MovieAward entity like box office and Imdb rating
     *
     * @param movieAwardsIdNomineeMap map with MovieAward entity id and movie title
     * @return data for update MovieAward entity with id and fields for update
     *
     * @see MovieAwardInfoForUpdateDto
     * @see MovieAwardEntity
     */
    List<MovieAwardInfoForUpdateDto> getOmdbDataForUpdateMovieAward(Map<Long, String> movieAwardsIdNomineeMap);
}
