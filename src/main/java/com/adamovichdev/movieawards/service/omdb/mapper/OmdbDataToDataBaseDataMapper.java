package com.adamovichdev.movieawards.service.omdb.mapper;

import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.dto.OmdbInfoDto;

public interface OmdbDataToDataBaseDataMapper {

    /**
     * maps data from OMDB API to data base format for update
     *
     * @param movieAwardId MovieAward entity id
     * @param omdbInfo data from OMDB
     * @return dto with data for update
     *
     * @see MovieAwardInfoForUpdateDto
     * @see OmdbInfoDto
     */
    MovieAwardInfoForUpdateDto map(Long movieAwardId, OmdbInfoDto omdbInfo);
}
