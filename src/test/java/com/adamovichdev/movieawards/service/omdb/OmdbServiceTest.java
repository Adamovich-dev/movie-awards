package com.adamovichdev.movieawards.service.omdb;

import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.dto.OmdbInfoDto;
import com.adamovichdev.movieawards.service.omdb.mapper.OmdbDataToDataBaseDataMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OmdbServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OmdbDataToDataBaseDataMapper omdbMapper;

    private OmdbService omdbService;

    @Before
    public void init() {
        omdbService = new OmdbService(omdbMapper, restTemplate);
    }

    @Test
    public void testGetOmdbDataForUpdateMovieAward() {
        Map<Long, String> movieAwardsIdNomineeMap = new HashMap<>();
        movieAwardsIdNomineeMap.put(1L, "title1");
        movieAwardsIdNomineeMap.put(2L, "title2");
        movieAwardsIdNomineeMap.put(3L, "title3");
        movieAwardsIdNomineeMap.put(4L, "title4");

        OmdbInfoDto successOmdbInfoDto = new OmdbInfoDto();
        successOmdbInfoDto.setTitle("title1");

        OmdbInfoDto wrongTitleOmdbInfoDto = new OmdbInfoDto();
        wrongTitleOmdbInfoDto.setTitle("title222222");

        ResponseEntity<OmdbInfoDto> successOmdbResponse = Mockito.mock(ResponseEntity.class);
        ResponseEntity<OmdbInfoDto> emptyBodyOmdbResponse = Mockito.mock(ResponseEntity.class);
        ResponseEntity<OmdbInfoDto> differentTitleOmdbResponse = Mockito.mock(ResponseEntity.class);
        when(emptyBodyOmdbResponse.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(successOmdbResponse.getBody()).thenReturn(successOmdbInfoDto);
        when(differentTitleOmdbResponse.getBody()).thenReturn(wrongTitleOmdbInfoDto);

        when(restTemplate.getForEntity(any(), eq(OmdbInfoDto.class), any(), eq("title1"))).thenReturn(successOmdbResponse);
        when(restTemplate.getForEntity(any(), eq(OmdbInfoDto.class), any(), eq("title2"))).thenReturn(differentTitleOmdbResponse);
        when(restTemplate.getForEntity(any(), eq(OmdbInfoDto.class), any(), eq("title3"))).thenReturn(emptyBodyOmdbResponse);
        when(restTemplate.getForEntity(any(), eq(OmdbInfoDto.class), any(), eq("title4"))).thenThrow(new RuntimeException());
        when(omdbMapper.map(any(), any())).thenReturn(new MovieAwardInfoForUpdateDto());

        List<MovieAwardInfoForUpdateDto> resultList = omdbService.getOmdbDataForUpdateMovieAward(movieAwardsIdNomineeMap);

        verify(restTemplate, times(4)).getForEntity(any(), any(), any(), any());
        verify(omdbMapper).map(any(), any());

        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
        assertEquals(resultList.size(), 1);
    }
}
