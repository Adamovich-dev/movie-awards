package com.adamovichdev.movieawards.dao.entity;

import com.adamovichdev.movieawards.dao.entity.enums.OskarCategory;
import com.adamovichdev.movieawards.dao.entity.enums.Won;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Data about movie award
 */
@Entity
@Table(name = "MOVIE_AWARDS")
public class MovieAwardEntity extends BaseEntity implements Serializable {

    /**
     * The year of oskar
     */
    @Column(name = "oskar_year")
    private String oskarYear;

    /**
     * Oskar category
     */
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private OskarCategory category;

    /**
     * Nominee (movie title)
     */
    @Column(name = "nominee")
    private String nominee;

    /**
     * additional information about nominee
     */
    @Column(name = "additional_info")
    private String additionalInfo;

    /**
     * Win oskar or not
     */
    @Column(name = "is_won")
    @Enumerated(EnumType.STRING)
    private Won isWon;

    /**
     * box office
     */
    @Column(name = "box_office")
    private Long boxOffice;

    /**
     * Imdb rating
     */
    @Column(name = "imdb_rating")
    private Double imdbRating;

    /**
     * User's grade
     */
    @OneToMany(mappedBy = "id.movieAwardId", fetch = FetchType.LAZY)
    private Set<RatingEntity> grades;

    public String getOskarYear() {
        return oskarYear;
    }

    public void setOskarYear(String oskarYear) {
        this.oskarYear = oskarYear;
    }

    public OskarCategory getCategory() {
        return category;
    }

    public void setCategory(OskarCategory category) {
        this.category = category;
    }

    public String getNominee() {
        return nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Won getIsWon() {
        return isWon;
    }

    public void setIsWon(Won isWon) {
        this.isWon = isWon;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Set<RatingEntity> getGrades() {
        return grades;
    }

    public void setGrades(Set<RatingEntity> grades) {
        this.grades = grades;
    }
}
