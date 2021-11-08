# Tests for the domain logic

This folder contains the logic tests for the main-class. The tests cover the main functionality of the project, so they do not cover getters, setters and built in java functions. 

## Testing

Running `mvn test` will run tests

## What is tested 

Currently the most important parts of the Main-class are logIn and createUser. The tests check that you can only log in to a registered user and exceptions are thrown when trying to log in to an unregistered user. You also have to conform your password when registering a user, so the test check that the confirmation pasword match. If you try to register with an email that is already in the system, you get an error.

Another part of the funtionality is hosting houses.Therefore, the last test checks that you can not host a house that is already registered. 

The rest of the methods in the Main-class are getters, setters, built in java-functions or rely on methods that are tested in JsonTest.java

### Code coverage

Running `mvn jacoco:report` will generate the file `housing/core/target/site/jacoco/index.html`.
Open this file in the browser to view the test coverage report.