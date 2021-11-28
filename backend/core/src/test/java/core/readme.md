# Tests for the domain logic

This folder contains the logic tests for the main-class. The tests cover the main functionality of the project, so they do not cover getters, setters and built in java functions. 

## Testing

Running `mvn test` will run tests

## What is tested 

# HouseTest 

Currently the most important parts of the Main-class are logIn and createUser. The tests check that you can only log in to a registered user and exceptions are thrown when trying to log in to an unregistered user. You also have to conform your password when registering a user, so the test check that the confirmation pasword match. If you try to register with an email that is already in the system, you get an error.

The removeUser test make sure that all houses connected to the user that is being removed gets deleted in the prosess. 

Another part of the funtionality is hosting houses.Therefore, one of the tests check that you can not host a house that is already registered. And another, that you cannot host a house when you are not logged in.

Other tests check that the methods provide the correct information 

The rest of the methods in the Main-class are getters, setters, built in java-functions or rely on methods that are tested in JsonTest.java

# UserTest
Only methods that are not getters and setters are the email validation method and constructor, so that is everthing that is getting tested. 

# HouseTest
HouseTest check that you cannot set a house that is already unavailable as unavailable. And the constructor gets tested. 

### Code coverage

Running `mvn jacoco:report` will generate the file `housing/core/target/site/jacoco/index.html`.
Open this file in the browser to view the test coverage report.