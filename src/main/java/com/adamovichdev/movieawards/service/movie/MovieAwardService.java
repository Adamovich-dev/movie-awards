package com.adamovichdev.movieawards.service.movie;

import com.adamovichdev.movieawards.dao.entity.MovieAwardEntity;
import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MovieAwardService {

    /**
     * Returns information about MovieAward's by page.
     * It's possible to pass sorting direction parameters, as well as an array with the names of the fields
     * by which sorting will be performed
     *
     * @param pageNumber number of page
     * @param pageSize number of element's per page
     * @param sortDirection DESK, ASK
     * @param sortFields can be any fields from MovieAwardEntity
     * @return View with short, main information about MovieAwardEntity
     *
     * @see MovieAwardEntity
     * @see MovieAwardShortViewProjection
     */
    Page<MovieAwardShortViewProjection> getMoviesShortInfoList(int pageNumber,
                                                               int pageSize,
                                                               Sort.Direction sortDirection,
                                                               String[] sortFields);

    /**
     * Returns fields id and nominee from all MovieAward's entity
     *
     * @return MovieAwardOmdbProjection
     *
     * @see MovieAwardOmdbProjection
     * @see MovieAwardEntity
     */
    List<MovieAwardOmdbProjection> getAllMovieAwardIdAndTitle();

    /**
     * Update MovieAward entity by information in MovieAwardInfoForUpdateDto
     *
     * @param info data for update
     *
     * @see MovieAwardInfoForUpdateDto
     * @see MovieAwardEntity
     */
    void updateMovieAwardData(MovieAwardInfoForUpdateDto info);
}
