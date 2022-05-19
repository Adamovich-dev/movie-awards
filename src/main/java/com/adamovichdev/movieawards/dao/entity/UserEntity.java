package com.adamovichdev.movieawards.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Data about user
 */
@Entity
@Table(name = "USER_TABLE")
public class UserEntity extends BaseEntity implements Serializable {

    /**
     * user's name
     */
    @Column(name = "user_name", length = 32, nullable = false)
    private String userName;

    /**
     * encrypted password
     */
    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @OneToMany(mappedBy = "id.userId", fetch = FetchType.LAZY)
    private Set<RatingEntity> grades;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RatingEntity> getGrades() {
        return grades;
    }

    public void setGrades(Set<RatingEntity> grades) {
        this.grades = grades;
    }
}
