   ### SOLUTION
   
Projections were used as return parameters to the repository. No need to implement mappers

Native SQL update queries were used. If the update is done through entities, then there will be an extra request first for select, then update all fields of the entity, even those that have not changed.

Parallel requests to the OMDB API and updating the MOVIE_AWARDS table improved the response time from 14.4 to 3 seconds.
