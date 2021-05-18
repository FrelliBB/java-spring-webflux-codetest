# Qudini Backend Software Engineer Code Test

## Requirements 

<details>
  <summary>Click to expand requirements</summary>

* Fork this project.
* Start project in the chosen framework.
* Provide an API which accepts a list of JSON `Customer` objects in the body of
  a POST request (see the JSON example attached). You can create this request
  via whatever tool you prefer, whether it's curl, Postman, a Spring webclient,
  or something else.
* The API should take this list of objects and sort them by `duetime` from
  oldest to newest and return it back as a sorted JSON array.
* Use Java 8's `datetime` package or Joda time (http://www.joda.org/joda-time/)
  library to handle times with timezones.
* The API should be non-blocking and be as efficient as possible in its sorting.
* We'll test this by load testing the project with a few hundred users to see
  how it performs. (If you have time try using JMeter to test your
  implementation.)

Bonus point:
* Add some tests to ensure your code works as expected. Is it better to test the
  controller or test the service layer directly? Either approach is valid so
  long as you can justify it.

</details>

## Running 

The repository provides a maven wrapper to enable running the application without requiring mvn to be installed. To run 
the application use:

```
mvnw.cmd spring-boot:run   # on windows
./mvnw spring-boot:run     # on linux or macos
```

The application will be started on the default port `8080`.

The repository contains a [`sort.http`](sort.http) file which can be executed from within IntellIJ in order to quickly verify that the 
service and sort endpoint are working as expected. 

## Implementation

### Spring WebFlux vs. MVC

One of the requirements for the application is to be "non-blocking". This can be achieved with both Spring Web and 
Spring WebFlux, but to different extents. Spring Web _can_ provide a non-blocking API by returning a `CompletableFuture` 
from the controller methods (see [Asynchronous Requests](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-async)),
but under the hood this is fundamentally different from how WebFlux handles asynchronous request.

To fulfill this requirement we opted for WebFlux to take advantage of its event loop threading model. Like everything, 
this comes with a handful of trade-offs:

- When using a reactive stack, all layers of the application, from web to messaging to database, need to be asynchronous 
or else the entire event loop is blocked.
  
- Reactive applications tend to be slightly harder to debug.

- WebFlux has a steeper learning curve compared to imperative programming, as such it might take longer for a new developer
to be onboarded onto a system or to train a junior engineer when using the reactive paradigm.

The [official documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-framework-choice)
has its own section covering the applicability of WebFlux which should be considered thoroughly before making that switch.

### Testing Approach

> Is it better to test the
controller or test the service layer directly? Either approach is valid so
long as you can justify it.

Testing the controller and the service layer are two distinctive layers of the testing pyramid, and we opted to test both.

The service layer tests in [CustomerServiceTest](src/test/java/com/example/demo/domain/CustomerServiceTest.java) can test 
purely the domain logic; in this case the sorting functionality. These tests can be executed quickly without needing to 
start up any Spring contexts. We can use this level of testing for checking various edge cases and specific scenarios.

The controller layer tests in [CustomerControllerTest](src/test/java/com/example/demo/web/CustomerControllerTest.java) 
will start up the Spring application test and in these tests we can verify things such as:

- Correct routing of requests
- Request deserialization and response deserialization of the `Customer` object (this can technically also be done in a 
  unit test, but we want to use the same `ObjectMapper` that the application configures)
- Web-level exception handling (4xx responses) if exception handling was implemented.

