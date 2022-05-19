package com.adamovichdev.movieawards.web.controler;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import com.adamovichdev.movieawards.service.facade.UpdateMovieAwardFacadeImpl;
import com.adamovichdev.movieawards.service.movie.MovieAwardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieAwardControllerTest {

    @Mock
    private MovieAwardServiceImpl movieAwardService;

    @Mock
    private UpdateMovieAwardFacadeImpl movieAwardFacade;

    private MovieAwardController movieController;

    @Before
    public void init() {
        movieController = new MovieAwardController(movieAwardService, movieAwardFacade);
    }

    @Test
    public void testGetShortMovieInfoList() {
        int pageNumber = 1, pageSize = 1;
        String [] sortedFields = new String[] {"boxOffice"};
        when(movieAwardService.getMoviesShortInfoList(pageNumber, pageSize, Sort.Direction.DESC, sortedFields)).thenReturn(new PageImpl<>(new ArrayList<>()));

        Page<MovieAwardShortViewProjection> page = movieController.getShortMovieInfoList(pageNumber, pageSize, Sort.Direction.DESC, sortedFields);

        verify(movieAwardService).getMoviesShortInfoList(pageNumber, pageSize, Sort.Direction.DESC, sortedFields);
        assertNotNull(page);
    }

    @Test
    public void testUpdateMovieAwardsFromImdb() {
        doNothing().when(movieAwardFacade).updateMovieAwardsFromImdb();

        ResponseEntity response = movieController.updateMovieAwardsFromImdb();

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
