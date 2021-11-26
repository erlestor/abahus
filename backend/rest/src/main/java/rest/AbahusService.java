package rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import core.Main;

public class AbahusService {
    
    private Main main;

    public void setMain(Main main) throws JsonParseException, JsonMappingException, IOException{
        this.main = new Main();
    }

    public Main getMain(){
        return this.main;
    }

}
