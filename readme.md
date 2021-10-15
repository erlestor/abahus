[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2129/gr2129.git)

# Group project GR21 29

We are making an application for renting houses.

## Architecture

The project uses maven.

Our code is inside of "housing", and inside of "main" we have made 3 java-files for the domaine logic, as well as a java-file for persistense with Json. There are Json files for storage in det data-folder. We have also made tests for the domaine logic. For the user interface we have made two controllers, one app-file and two FXML-files. You are able to create a user from the dashboard and then log into that user later on.

## Workflow and code quality

Once the specifications for the next delivery is published we have a meeting where we discuss which issues need to be implemented for the next release. These issues are then distributed amongst the pairs in our group. The pairs then work together in a branch tied to that issue. When the branches are ready to be merge another meeting is held where all the members collaborate on the merge to make sure all the correct changes are merged in.

For code quality we make sure we test each module. Core and Json modules are tested using JUnit tests and UI tests are done using TestFx. To see how much test coverage we have we use Jacoco. The goal is to keep test coverage as close as possible to 100 %.

## How to run

Run these commands inside "housing":

- mvn install
- cd ui
- mvn javafx:run
