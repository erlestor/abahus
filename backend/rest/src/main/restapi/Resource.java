package gr2129.restapi;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.core.AbstractBackend;
import backend.core.House; 
import backend.core.User;
import backend.core.Main;
import backend.core.Model; 
import backend.jsonworker.Persistence; 


@Produces(MediaType.APPLICATION_JSON)
public class Resource {

  private static final Logger LOG = LoggerFactory.getLogger(Resource.class);

  private final Model Model;
  private final String name;
  private final AbstractBackend backend; //endre alle disse 

  @Context
  private Persistence persistence;

  public void setPersistence(Persistence persistence) {
    this.persistence = persistence;
  }
  

   //konstruktør 
  public resource(Model model, String name, AbstractBackend backend) {
    this.model = model;
    this.name = name;
    this.backend = backend;
  }

  private void checkBackend() {
    if (this.backend == null) {
      throw new IllegalArgumentException("No House named \"" + name + "\"");
    }
  }

 
  @GET
  public AbstractBackend getBackend() {
    checkBackend();
    LOG.debug("getBackend({})", name);
    return this.backend;
  }

  private void autoSaveModel() {
    if (persistence != null) {
      try {
        persistence.saveModel(model);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Couldn't auto-save model: " + e);
      }
    }
  }

 
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public boolean putBackend(AbstractBackend BackendArg) {
    LOG.debug("putBackend({})", BackendArg);
    AbstractBackend oldBackend = this.model.putBackend(BackendArg);
    autoSaveModel();
    return oldBackend == null;
  }

 
  @PUT
  public boolean putBackend() {
    return putBackend(new Backend(name));
  }

  
  @POST
  @Path("/rename")
  public boolean renameBackend(@QueryParam("newName") String newName) {
    checkBackend();
    if (this.model.getBackend(newName) != null) {
      throw new IllegalArgumentException("A house named \"" + newName + "\" already exists");
    }
    this.backend.setName(newName);
    autoSaveModel();
    return true;
  }

 
  @DELETE
  public boolean removeBackend() {
    checkBackend();
    this.model.removeBackend(this.backend);
    autoSaveModel();
    return true;
  }
}