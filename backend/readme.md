# Group gr2129 repository

## Description of the app

We intend to write a AirBnB-inspired house renting app.
The dashboard of the app will display housing that is available for rent.
The user can register, and thereafter post houses that will be available at the dashboard.
Registered users can send email requests for rent.
Booking requests will be available to the landlord.

## Workflow and code quality

Once the specifications for the next delivery is published we have a meeting where we discuss which issues need to be implemented for the next release. These issues are then distributed amongst the pairs in our group. The pairs then work together in a branch tied to that issue. When the branches are ready to be merge another meeting is held where all the members collaborate on the merge to make sure all the correct changes are merged in.

For code quality we make sure we test each module. Core and Json modules are tested using JUnit tests and UI tests are done using TestFx. To see how much test coverage we have we use Jacoco. The goal is to keep test coverage as close as possible to 100 %.

# User stories

## Register (us-1)

As a private person I wish to register a user.

The user needs to be able to enter email and password into a form such that this is saved
and the user can login later. The user then wants to be automatically logged in and sent
to the main dashboard.

## Login (us-2)

A user wishes to login to a previously created account using email and password.
The user then wants to be sent to the main dashboard.

## View available houses (us-3)

A user wishes to view the houses that are available

## Add and delete houses (us-4)

The user needs to be able to add a new house with location such that other users can see the house.
Then the user should be able to do delete any of his listed houses.
