CREATE TABLE IF NOT EXISTS USER_TABLE (
    id              INTEGER         NOT NULL AUTO_INCREMENT,
    user_name       VARCHAR(32)     NOT NULL UNIQUE,
    password        VARCHAR(64)     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `MOVIE_AWARDS` (
    id              INTEGER         NOT NULL AUTO_INCREMENT,
    oskar_year      VARCHAR(64)     NOT NULL,
    category        VARCHAR(64)    NOT NULL,
    nominee         VARCHAR(128)     NOT NULL,
    additional_info VARCHAR(256)   NOT NULL,
    is_won          VARCHAR(3)      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS USER_MOVIE_RATING (
    movie_award_id       INTEGER         NOT NULL,
    user_id              INTEGER         NOT NULL,
    grade                INTEGER(2)      NOT NULL,
    PRIMARY KEY (movie_award_id, user_id),
    CONSTRAINT FK_Rating_Move FOREIGN KEY (movie_award_id)
        REFERENCES MOVIE_AWARDS(id),
    CONSTRAINT FK_Rating_User FOREIGN KEY (user_id)
        REFERENCES USER_TABLE(id)
);
