package com.adamovichdev.movieawards.service.movie;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import com.adamovichdev.movieawards.dao.repository.MovieAwardRepository;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.adamovichdev.movieawards.Util.LoggerUtil.logRequest;

@Service
public class MovieAwardService {

    private static final Logger logger = LoggerFactory.getLogger(MovieAwardService.class);

    private final MovieAwardRepository movieRepository;

    @Autowired
    public MovieAwardService(MovieAwardRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public Page<MovieAwardShortViewProjection> getMoviesShortInfoList(int pageNumber, int pageSize, Sort.Direction sortDirection, String[] sortFields) {
        final String methodName = "getMoviesShortInfoList";
        logRequest(logger, methodName, pageNumber, pageSize, sortDirection, Arrays.toString(sortFields));

        final Sort sort = Sort.by(sortDirection, sortFields);
        final PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,  sort);
        return movieRepository.getAllMoviesShortInfo(pageRequest);
    }

    @Transactional(readOnly = true)
    public List<MovieAwardOmdbProjection> getAllMovieAwardIdAndTitle() {
        return movieRepository.getAllIdAndTitle();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateMovieAwardData(MovieAwardInfoForUpdateDto info) {
        final String methodName = "updateMovieAwardData";
        logRequest(logger, methodName, info);

        final Long boxOffice = info.getBoxOffice();
        final Double imdbRating = info.getImdbRating();
        final Long id = info.getMovieAwardId();
        movieRepository.updateBoxOfficeAndOmdbRatingById(id, boxOffice, imdbRating);
    }
}
