INSERT INTO USER_TABLE (user_name, password) VALUES
('user1', '123'),
('user2', '456');

INSERT INTO MOVIE_AWARDS (oskar_year, category, nominee, additional_info, is_won, box_office, imdb_rating) VALUES
('2010 (83rd)','Best Picture','Black Swan','Mike Medavoy, Brian Oliver and Scott Franklin, Producers','NO', 987, 3),
('2010 (83rd)','Best Picture','The Fighter','David Hoberman, Todd Lieberman and Mark Wahlberg, Producers','NO', 654, 4),
('2010 (83rd)','Best Picture','Inception','Emma Thomas and Christopher Nolan, Producers','NO', 321, 5);


INSERT INTO USER_MOVIE_RATING VALUES
(1, 1, 10),
(2, 1, 3);
