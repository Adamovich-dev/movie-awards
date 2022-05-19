package com.adamovichdev.movieawards.service.movie;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import com.adamovichdev.movieawards.dao.repository.MovieAwardRepository;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieAwardServiceImplTest {

    @Mock
    private MovieAwardRepository movieRepository;

    private MovieAwardServiceImpl movieAwardService;

    @Before
    public void init() {
        movieAwardService = new MovieAwardServiceImpl(movieRepository);
    }

    @Test
    public void testGetMoviesShortInfoList() {
        when(movieRepository.getAllMoviesShortInfo(any(PageRequest.class))).thenReturn(new PageImpl(new ArrayList()));

        Page<MovieAwardShortViewProjection> resultPage = movieAwardService.getMoviesShortInfoList(1, 1, Sort.Direction.DESC, new String[] {"1234"});

        verify(movieRepository).getAllMoviesShortInfo(any(PageRequest.class));

        assertNotNull(resultPage);
    }

    @Test
    public void testGetAllMovieAwardIdAndTitle() {
        when(movieRepository.getAllIdAndTitle()).thenReturn(null);

        movieAwardService.getAllMovieAwardIdAndTitle();

        verify(movieRepository).getAllIdAndTitle();
    }

    @Test
    public void testUpdateMovieAwardData() {
        MovieAwardInfoForUpdateDto dto = new MovieAwardInfoForUpdateDto();
        dto.setMovieAwardId(1L);
        dto.setImdbRating(2.0);
        dto.setBoxOffice(10L);
        doNothing().when(movieRepository).updateBoxOfficeAndOmdbRatingById(1L, 10L, 2.0);

        movieAwardService.updateMovieAwardData(dto);

        verify(movieRepository).updateBoxOfficeAndOmdbRatingById(1L, 10L, 2.0);
    }
}
