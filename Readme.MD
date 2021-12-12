###

Backend coding challenge: https://gist.github.com/wbaumann/aaa5ef095e213ffbea35b7ca3cc251a7



### Local run

- Run mongo DB container: `docker-compose up`, be careful that default mongodb port is free
- In Intellij run main class: `CinemaApplication`
- Put imdb key in file `application.yaml`

Swagger endpoint is here: http://localhost:8080/swagger-ui/


### Remarks

- Spring Boot Application with Webflux but Reactor types were transformed to 
coroutines and `suspend` functions were used mostly in application code
- Used MongoDB as persistent layer
- Both `coroutines` and `MongoDb` where rather new things to me, so 
I treated this challenge also as opportunity to learn new things.
- Used hexagonal architecture
- Divided domain between three subdomains(rating/movie/catalog)
- All domain code is located in package `domain`and it's independent from Spring
- Adapters and rest controllers were put in `infrastructure` package
- All adapters were tested with TestContainers(MongoDB) and Wiremock
- Created one E2E test covered common actions - [E2Etes](src/test/kotlin/com/mziolo/cinema/e2e/E2EFlowTest.kt)
- Domain were covered by Unit Tests
- Exception handling needs to be improved(just implemented very basic)
- There is no schema validation at all(TODO)
- Swagger was generated based on code(Spring Fox)
- I would consider different approach, prepare openAPi spec and generate dto from it
- Challenge isn't implemented with fully functional style(couple side effects methods which throw exceptions which are caught in controller)
Should be return proper `Result` from each of these
- Implemented checking if show times don't overlap each other
but it's need to be improved(proper synchronization) 
- Security is not implemented at all(authentication/authorization)
- There is not configured resiliency aspects at all (webclient - timeouts/retries/cb), 
this needs to be improved(possible service mesh could solve couple issues)

