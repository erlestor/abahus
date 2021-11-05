package backend.restapi;

import backend.core.User; 



@Path(AbahusService.ABAHUS_SERVICE_PATH)
@Path ("/json/user")
public class AbahusService {

    private String email;
    private String password; 


    @GET 
    @Path ("/get")
    @Produces ("app/json")
    public User getUserinJSON(){
        User user = new User(email, password);
        user.getEmail();
        user.getPassword();
    }

    @Path ("/json/logIn")

    @Path ("json/house")

   



}