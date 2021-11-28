# Backend

## tests

- Running `mvn test` will run tests
- Running `mvn jacoco:report` will generate the file `housing/json/target/site/jacoco/index.html`. Open this file in the browser to view the test coverage report.
- For information about what it tested see [core readme](core/src/test/java/core/readme.md) and [jsonworker readme](core/src/test/java/jsonworker/readme.md)

## how to run rest server

- Inside backend run the command "mvn install"
- If you are using gitpod. Open [AbahusApplication](rest/src/main/java/rest/AbahusApplication.java) and make sure that your current gitpod url is inside .allowedOrigins(). A comment will guide you.
- Then inside backend/rest run the command "chmod +x mvnw" and then "./mvnw spring-boot:run"

## architecture and frameworks

See [gr2129/readme](../readme.md) for more information
