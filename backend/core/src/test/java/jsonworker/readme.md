# JSON MODULE

## Testing

Running `mvn test` will run tests

## HouseTest

Everything but the getters, simple setters and toString-methods are tested.Tests cover the constructor and that each house has to have both an owner and a location. One of the tests cover that you cannot set an already unavailable house as unavailable.

## UserTest

The only things that needed to be tested in the UserTest-class was the constructor and checkEmail-method.

## JsonworkerTest

Testing all the main function of the class.

### Code coverage

Running `mvn jacoco:report` will generate the file `housing/json/target/site/jacoco/index.html`.
Open this file in the browser to view the test coverage report.
