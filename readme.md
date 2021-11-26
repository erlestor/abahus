[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2129/gr2129.git)

# Group project GR21 29

## About the app

We are making an app for renting houses. A user should be able to register/log in and then see available houses. The user can then contact the owner. Additionally, the user can add his own house to the app and rent it by changing the availability or delete the house.

Our code is divided into our backend and frontend folders. Backend consists of the domain and rest-api written in java. Frontend is written in react. Tests for frontend are located in frontend/src/tests. Tests for backend are located in each modules src/main/test folder. See their respective readmes for more information. 

## How to run

- How to run frontend: [frontend/readme.md](frontend/readme.md)
- How to run backend: [backend/readme.md](backend/readme.md)

## Tools for testing code quality

- Prettier: in frontend prettier makes sure that code is properly formatted.
- Checkstyle: checks that our source code corresponds to java standards
- Jacoco: gives us test-covereage as percentage

## Architecture

The application is divided into backend and frontend folder. Frontend is a create-react-app project which used react and tailwind. Backend is a multi-module maven project which is divided into the following modules:

core - contains the domain-layer of the application.

rest - contains our rest-api which connects our frontend to our backend. 

The core module consits of multiple classes, implementing the core logic for the applications functionality. Due to most classes in core being public, they can be called upon in our rest module.

See image for illustation of the architecture of the modules.  
![diagram](diagram.png)

## Git conventions

- An issue should be either a user story or a specific technical issue. Eg. "add log-in form to frontend" and "make log in endpoint in rest-api". The issue should not include too many different files.
- A branch is created when work is started on a specific issue. The branch should include the issue number. Eg. "2-add-login-form-to-frontend".
- Commit messages should be concise. Instead of writing "added button and fixed filename" divide it up to two different commits.
- When the issue is solved in the current branch a merge request is created with "Closes #issue_number" in its description. Another person then approves and tests the merge locally to make sure that the changes don't cause unexpected issues. Only then can it be merged.

## Data

User data is stored in json files. A user is a json object with username and password. A house is a json object with location, availability and the corresponding json user object.

## Additional comments

### Jsonworker

We have chosen to use static methods in Jsonworker since the functions are equal for all instances of the class. This is because Jsonworker has no state and is simply a utility-class for writing to json.

### Storage

To store data, a user needs to press a button instead of implicitly. This is because our data doesn't change that often. Eg. a house doesn't change location often and houses are not rented multiple times per day. This was written according to a remark from our student assistant.
