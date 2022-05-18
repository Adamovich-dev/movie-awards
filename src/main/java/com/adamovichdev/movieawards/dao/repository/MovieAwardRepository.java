package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.MovieAwardEntity;
import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardOmdbProjection;
import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieAwardRepository extends JpaRepository<MovieAwardEntity,Long> {

    @Query(nativeQuery = true, value = "select id, nominee as title, oskar_year as oskarYear,is_won as isWon, box_office as boxOffice " +
            "from movie_awards as a " +
            "left join user_movie_rating as b " +
            "on a.id = b.movie_award_id group by title, oskarYear,isWon",
    countQuery = "select count(id) from movie_awards")
    Page<MovieAwardShortViewProjection> getAllMoviesShortInfo(Pageable page);

    @Query(nativeQuery = true, value = "select id, nominee as title from movie_awards")
    List<MovieAwardOmdbProjection> getAllIdAndTitle();

    @Modifying
    @Query("update MovieAwardEntity m set " +
            "m.boxOffice = ifnull(:boxOffice, m.boxOffice), " +
            "m.imdbRating = ifnull(:imdbRating, m.imdbRating) " +
            "where m.id = :id")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateBoxOfficeAndOmdbRatingById(@Param("id") Long id,
                                          @Param("boxOffice") Long boxOffice,
                                          @Param("imdbRating") Double imdbRating);
}
