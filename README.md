# spring cloud playground project

- inspired by `https://spring.io/guides/gs/routing-and-filtering/`
- and developed reading `spring micro service in action`

## Status
- `gradle` project has 4 modules
  -  `common` a library with common part necessary to every `microservice` of the project.
  - `gateway` use `Zul` to add `correlationId	` to every request.
    - provide routing rules, so other `microservice` are accessed through it, and the correlation ID is then added.
    - default port: `8080`
    
    - `book` sample `microservice` with `book/available` and `book/checked-out` rest endpoint.
      - default port: `8090`
    
    - zipkinServer: default port is `9411`, should be started first, else lot of exception in app
    
direct access:    				http://localhost:8090/available
access through gateway: 		http://localhost:8080/books/available
- correlation id is set as the header: `tmx-correlation-id` of the request forwarded from `gateway` to `book`: `RequestFilter`
- it is also added in the header of the response by the `gateway`: `ResponseFilter`

- !!! Playing with ZipKin is now working, from 1 call to the `gateway`, I can see the trace up to the `book` application 
  
  
## Todo

### Find how to move spring-dependencie plugin declaration at top gradle project  