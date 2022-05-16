package com.adamovichdev.movieawards.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "MOVIE_AWARDS")
public class MovieAwardEntity extends BaseEntity implements Serializable {

    @Column(name = "oskar_year")
    private String oskarYear;

    @Column(name = "category")
    private String category;

    @Column(name = "nominee")
    private String nominee;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "is_won")
    private String isWon;

    @OneToMany(mappedBy = "id.movieAwardId", fetch = FetchType.EAGER)
    private Set<RatingEntity> grades;

    public String getOskarYear() {
        return oskarYear;
    }

    public void setOskarYear(String oskarYear) {
        this.oskarYear = oskarYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getIsWon() {
        return isWon;
    }

    public void setIsWon(String isWon) {
        this.isWon = isWon;
    }

    public Set<RatingEntity> getGrades() {
        return grades;
    }

    public void setGrades(Set<RatingEntity> grades) {
        this.grades = grades;
    }
}
