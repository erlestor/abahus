# Rest-api Documentation

## General info

rest server is hosted on localhost:8080

## Endpoints

### registerUser

username, password and confirmpassword are sent in body.
returns username

### logIn

username, password is sent in body.
returns username

### getHouse/{location}

location is sent in header.
returns house

### houses

returns all houses

### addHouse/{location}

location is sent in header.
returns "House is added" if successful

### removeHouse/{location}

location is sent in header.
returns feedback message
