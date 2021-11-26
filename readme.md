[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2129/gr2129.git)

# Group project GR21 29

We are making an application for renting houses.

The project uses maven.

Our code is inside of "housing", and is divided with a three layer arcitecture, with maps for Json, Main and UI. More information about the aplication is in the readme-file inside of housing. The test-folders contain all the information about the test. See the ui-testing branch for information about the ui-tests and the tests as far as we have them.

You are able to create a user from the dashboard and then log into that user later on. With that user you can post and delete houses for rent, and se other users houses.

## How to run

- How to run frontend: [frontend/readme.md](frontend/readme.md)

## Architecture

The application is implemented with a multi-module filestructure using Maven:

ui - contains the graphical user interface, using fxml.

core - contains the domain-layer of the application.

persistence - contains classes for writing to, and saving information in a file using json and jackson.

The core module consits of multiple classes, implementing the core logic for the applications functionality. Since most methods in these classes Due to most classes in core are public, they can be called upon in the classes in other modules. This is done in the ui module to implement the logic in a graphical user interface. The persistence module creates objects of the core classes, and uses methods from them to implement correct file-manangement for the application. In this sense, the modules are codependent.

See image for illustation of the architecture of the modules.  
![packageDiagram](packageDiagram.png)
