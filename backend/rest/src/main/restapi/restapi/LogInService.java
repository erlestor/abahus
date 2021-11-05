package backend.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import backend.restapi.LogInResource;
import backend.core.User;
import backend.core.Main;
import backend.core.House; 


@Path(LogInService.LOGIN_SERVICE_PATH)
@Path ("/json/user")
public class LogInService {

    private String email;
    private String password; 

    private LogInResource logInResource; 

    private static final String LOGIN_SERVICE_PATH = "logIn"; 

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PUT
    @Path("/registerUser/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public in addUser(final LogInResource logInResource){
        User user = logInResource.getUser();
        String password = logInResource.getPassword();
        //ikke ferdig 

        
    }




    


    @GET 
    @Path ("/get")
    @Produces ("app/json")
    public User getUserinJSON(){
        User user = new User(email, password);
        user.getEmail();
        user.getPassword();
    }

    //@Path ("/json/logIn")

    //@Path ("json/house")

   



}