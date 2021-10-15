#Test for Housing Application 

#what is tested 

AppTest.java tests the Dashboardcontroller and MainController to make sure they work correctly. 

testHandleLogIn tests that the handleLogIn method works as it is supposed with correct inputs in the textfields. It also tests that there are error message labels that pop up if the input is wrong. 

testHandleCreateUser test that the handleCreateUSer method works as it is supposed with correct inputs in the textfields. It also tests that there are error message labels that pop up if the input is wrong. 

testSendMain tests that when the login button is pressed, it sends to the mainController. 

Running the AppTest in Gitpod gives compilation failure. The code does not have errors as we can see. Talked to student assistens who said ui tests can get build failures in Gitpod. We will fix this.  