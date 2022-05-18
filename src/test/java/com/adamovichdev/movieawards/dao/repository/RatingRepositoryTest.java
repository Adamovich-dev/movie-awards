package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.projection.RatingViewProjection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql(scripts = "/init_db.sql")
@Sql(scripts = "/insert_data.sql")
public class RatingRepositoryTest {

    @Autowired
    private RatingRepository repository;

    @Test
    public void testGetGradeByUserIdAndTitleId() {
        RatingViewProjection view = repository.getRatingViewByUserIdAndTitleId(1L, 1L);

        assertNotNull(view);
        assertNotNull(view.getGrade());
        assertEquals(view.getUserId(), Long.valueOf(1L));
        assertEquals(view.getMovieAwardId(), Long.valueOf(1L));
    }

    @Test
    public void testInsertTitleRatingFromUser() {
        RatingViewProjection nullResult = repository.getRatingViewByUserIdAndTitleId(2L, 2L);
        assertNull(nullResult);

        repository.insertTitleRatingFromUser(2L, 2L, 2);

        RatingViewProjection result = repository.getRatingViewByUserIdAndTitleId(2L, 2L);

        assertNotNull(result);
        assertEquals(result.getGrade(), Integer.valueOf(2));
        assertEquals(result.getUserId(), Long.valueOf(2));
        assertEquals(result.getMovieAwardId(), Long.valueOf(2));
    }

    @Test
    public void testUpdateGradeByUserIdAndTitleId() {
        RatingViewProjection notUpdatedData = repository.getRatingViewByUserIdAndTitleId(1L, 1L);
        assertNotNull(notUpdatedData);
        assertNotEquals(notUpdatedData.getGrade(), Integer.valueOf(1));

        repository.updateGradeByUserIdAndTitleId(1L, 1L, 1);

        RatingViewProjection updateData = repository.getRatingViewByUserIdAndTitleId(1L, 1L);
        assertNotNull(notUpdatedData);
        assertEquals(updateData.getGrade(), Integer.valueOf(1));
    }
}
