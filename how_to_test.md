## HOW TO TEST?

This app uses H2 in-memory DB, after restarting all data will be lost.
Start SQL scripts - data.sql and schema.sql in /resources

3 users available with userName and password
* user1 / pwd1
* user2 / pwd2
* user3 / pwd3

-- Postman or similar app installed 

-- Get jwt token by sending POST request to url http://localhost:8080/authenticate

example request body:

{
"userName": "user3",
"password": "pwd3"
}

Save the jwt token, for subsequent requests, you must pass it in the Authorization header with the "Bearer" prefix

example response body from /authenticate

{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImV4cCI6MTY1Mjk4NjUxNCwidXNlcklkIjozLCJpYXQiOjE2NTI5Njg1MTR9.zc9PS1_fYL3g7UbNDvkbaytkVRAt4y02fu-8uBrMXbY"
}

### H2 Console

Console H2 available from url http://localhost:8080/h2-console/

These are login settings.
![img.png](src/main/resources/static/img/H2_console.png)

There you can get acquainted with the structure of tables and data

 ***To complete the task, only the data for the Best Film category was transferred in MOVIE_AWARDS table***

USER_TABLE contains user date

USER_MOVIE_RATING contains data with ratings from app users to movie

### Test app API

-- **OMDB API call and db update**

As you can see, the columns box_office and imdb_rating are empty

To update values from OMDB, needs to send a POST request with an empty body to url http://localhost:8080/api/movie-award/update/from-omdb

After finishing box_office and imdb_rating will be updated

But, you can see in the console by word ERROR that some movies were not updated, because there is a check for the same name of the movie sent in the request and received in the response. If the names are different, the update does not occur

in case like: OmdbService return movie with another title, search was by: The Godfather, Part III, return: The Godfather: Part III - fixed with updating nominee title

but in other case like: OmdbService return movie with another title, search was by: Cries and Whispers, return: On Solace: Video Essay for Cries and Whispers - it's different films

also in the CSV file, there were several entries where additional info and nominee were mixed up. It was fixed

-- **Top box office rating**

To get a list of the top 10 films by box office, needs to send a GET request without params to url http://localhost:8080/api/movie-award/short-info-list

Returns the first page of 10 films sorted in DESC order

We can override default values of search parameters, for example send this request
http://localhost:8080/api/movie-award/short-info-list?pageNumber=0&pageSize=10&sortDirection=DESC&sortFields=imdbRating

-- **User Rating**

To get information about whether the user has rated the movie, needs to send a GET request to the url http://localhost:8080/api/rating/{userId}/by-title/{movieAwardId}

To save a movie user's rating, needs to send a POST request to the url http://localhost:8080/api/rating/save
with body {
"userId": 3,
"titleId": 5,
"grade": 10
}

To update a movie user's rating, needs to send a POST request to the url http://localhost:8080/api/rating/update
with body {
"userId": 3,
"titleId": 5,
"grade": 3
}






