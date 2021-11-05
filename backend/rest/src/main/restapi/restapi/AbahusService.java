package rest.restapi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import backend.core.AbstractMain; 
import backend.core.Model;
import backend.jsonworker.Persistence; 
import backend.core.Main;


@Path (ModelService.MODEL_SERVICE_PATH)
@Procedures (MediaType.APPLICATION_JSON)
public class ModelService {

    public static final String MODEL_SERVICE_PATH = "lagdette?";

    private static final Logger LOG = LoggerFactory.getLogger(ModelService.class);

    @Context
    private Model model;

    @Context
    private Persistence persistence;

    @GET
    public Model getModel(){
        LOG.debug("getModel: " + model);
        return model; 
    }

    @Path
    public SettingsResource getSettings(){
        LOG.debug("Sub-resource for Settings");
        return new SettingsResource(model);
    }

    @Path("/house")
    public Resource getMain (@PathParam("name" String name)) {
        AbstractMain main = getModel().getMain(name);
        LOG.debug("Sub-resource for Backend " + name + ":" + main);
        Resource resource = new Resource(model, name, main);
        resource.setPersistence(persistence);
        return resource;
    }

    @Path("/user")
    public Resource getUser (@PathParam ("user") String user) {
        
        Resource resource = 
    }






    

    




}