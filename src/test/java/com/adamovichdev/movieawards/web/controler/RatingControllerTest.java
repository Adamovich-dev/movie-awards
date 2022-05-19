package com.adamovichdev.movieawards.web.controler;

import com.adamovichdev.movieawards.dao.entity.projection.RatingViewProjection;
import com.adamovichdev.movieawards.data.RatingViewProjectionImp;
import com.adamovichdev.movieawards.service.rating.RatingServiceImpl;
import com.adamovichdev.movieawards.web.api.rating.RatingDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RatingControllerTest {

    @Mock
    private RatingServiceImpl ratingService;

    private RatingController controller;

    private RatingDto rating = new RatingDto();

    @Before
    public void init() {
        controller = new RatingController(ratingService);
    }

    @Test
    public void testGetRatingByUserIdAndTitleId() {
        when(ratingService.getRatingByUserIdAndTitleId(1L, 1L)).thenReturn(new RatingViewProjectionImp());

        RatingViewProjection response = controller.getRatingByUserIdAndTitleId(1L, 1L);

        verify(ratingService).getRatingByUserIdAndTitleId(1L, 1L);
        assertNotNull(response);
    }

    @Test
    public void testSaveRating() {
        doNothing().when(ratingService).saveRating(rating);

        ResponseEntity response = controller.saveRating(rating);

        verify(ratingService).saveRating(rating);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateRating() {
        doNothing().when(ratingService).updateRating(rating);

        ResponseEntity response = controller.updateRating(rating);

        verify(ratingService).updateRating(rating);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
