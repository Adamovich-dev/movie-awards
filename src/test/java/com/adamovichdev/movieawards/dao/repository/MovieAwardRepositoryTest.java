package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql(scripts = "/init_db.sql")
@Sql(scripts = "/insert_data.sql")
public class MovieAwardRepositoryTest {

    @Autowired
    private MovieAwardRepository repository;

    @Test
    public void testGetAllMoviesShortInfo() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<MovieAwardShortViewProjection> page = repository.getAllMoviesShortInfo(pageRequest);

        assertNotNull(page);
        assertTrue(page.isLast());
        assertNotNull(page.getContent());
        List<MovieAwardShortViewProjection> content = page.getContent();
        assertEquals(content.size(), 3);
    }

    @Test
    public void testGetAllIdAndTitle() {
        List<MovieAwardOmdbProjection> allIdAndTitle = repository.getAllIdAndTitle();

        assertNotNull(allIdAndTitle);
        assertEquals(allIdAndTitle.size(), 3);
    }
}
