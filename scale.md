### SCALE

 - Split app to microservices by domain and function
 - CI/CD with Docker or other container and Kubernetes/OpenShift (K/OS) as container manager. K/OS can scale services up/down depending on the load on a particular service. 
 - Also, if some service has a high load on a GET request that requires data from many other microservices, it is possible to implement the CQRS pattern
