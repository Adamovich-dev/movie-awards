## TO DO

### missing:

the best solution was to separate the MovieAwards table into information about the movie and the information about the nomination. One film - many nominations, one nomination for one film

not very important for goals of test-task:
* create/update userEntity, user's Roles
* JWT refresh token
* add Docker

### todo

- return in response fail tetle list
- Logging with AOP or BPP
- call update data from OMDB API from some scheduler (Once a day/hour for example)
- It will be match better if request's to OMDB API will sends by omdbId, not tittle
- Not embedded data base
- App profiles (dev/prod)
- CI/CD with Docker, Kubernetes/OpenShift
