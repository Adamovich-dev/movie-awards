package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.RatingEntity;
import com.adamovichdev.movieawards.dao.entity.RatingId;
import com.adamovichdev.movieawards.dao.entity.projection.RatingViewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {

    @Query(nativeQuery = true, value = "select grade, user_id as userId, movie_award_id as movieAwardId from USER_MOVIE_RATING where user_id = :userId AND movie_award_id = :titleId")
    RatingViewProjection getGradeByUserIdAndTitleId(@Param("userId") Integer userId,
                                                    @Param("titleId") Integer titleId);

    @Modifying
    @Query(nativeQuery = true, value = "insert into USER_MOVIE_RATING values (:titleId, :userId, :grade)")
    void insertTitleRatingFromUser(@Param("titleId") Integer titleId,
                                   @Param("userId") Integer userId,
                                   @Param("grade") Integer grade);

    @Modifying
    @Query(nativeQuery = true, value = "update USER_MOVIE_RATING r set r.grade = :grade " +
            "where r.user_id = :userId and r.movie_award_id = :titleId")
    void updateGradeByUserIdAndTitleId(@Param("titleId") Integer titleId,
                                       @Param("userId") Integer userId,
                                       @Param("grade") Integer grade);

}
