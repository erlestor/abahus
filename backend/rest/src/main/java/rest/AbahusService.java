package rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import core.Main;

public class AbahusService {
    
    private Main main;

    public AbahusService() throws JsonParseException, JsonMappingException, IOException{
       this.main = new Main();
    }

    public void logIn(String email, String passord){
        this.main.logInUser(email, passord);
    }

    public void register(String email, String passord, String confirmPassord) throws IllegalArgumentException, IOException{
        this.main.registerUser(email, passord, confirmPassord);
    }


    public Main getMain(){
        return this.main;
    }

}
