package com.adamovichdev.movieawards.service.facade;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.data.MovieAwardOmdbProjectionImpl;
import com.adamovichdev.movieawards.service.movie.MovieAwardServiceImpl;
import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.OmdbServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateMovieAwardFacadeImplTest {

    @Mock
    private MovieAwardServiceImpl movieAwardService;
    @Mock
    private OmdbServiceImpl omdbService;

    private UpdateMovieAwardFacadeImpl facade;

    @Before
    public void init() {
        facade = new UpdateMovieAwardFacadeImpl(movieAwardService, omdbService);
    }

    @Test
    public void testUpdateMovieAwardsFromImdb() {
        MovieAwardInfoForUpdateDto updateDto1 = new MovieAwardInfoForUpdateDto();
        MovieAwardInfoForUpdateDto updateDto2 = new MovieAwardInfoForUpdateDto();
        List<MovieAwardInfoForUpdateDto> infoForUpdateList = List.of(updateDto1, updateDto2);
//        when(movieAwardService.getMoviesShortInfoList(anyInt(), anyInt(), any(Sort.Direction.class), any())).thenReturn()
        when(movieAwardService.getAllMovieAwardIdAndTitle()).thenReturn(getMovieAwardOmdbProjectionList());
        when(omdbService.getOmdbDataForUpdateMovieAward(any())).thenReturn(infoForUpdateList);
        doNothing().when(movieAwardService).updateMovieAwardData(updateDto1);
        doThrow(new RuntimeException()).when(movieAwardService).updateMovieAwardData(updateDto2);

        facade.updateMovieAwardsFromImdb();

        verify(movieAwardService).getAllMovieAwardIdAndTitle();
        verify(omdbService).getOmdbDataForUpdateMovieAward(any());
        verify(movieAwardService, times(infoForUpdateList.size())).updateMovieAwardData(any(MovieAwardInfoForUpdateDto.class));
    }

    public static List<MovieAwardOmdbProjection> getMovieAwardOmdbProjectionList() {
        return List.of(
                new MovieAwardOmdbProjectionImpl(1L, "title1"),
                new MovieAwardOmdbProjectionImpl(2L, "title2"),
                new MovieAwardOmdbProjectionImpl(3L, "title3")
        );
    }
}
