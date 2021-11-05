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

import backend.core.AbstractMain;
import backend.core.Main;
import backend.core.Model; 
import backend.jsonworker.Persistence; 


@Produces(MediaType.APPLICATION_JSON)
public class Resource {

  private static final Logger LOG = LoggerFactory.getLogger(Resource.class);

  private final Model Model;
  private final String name;
  private final AbstractMain main; //endre alle disse 

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

  private void checkMain() {
      if (this.main == null) {
        throw new IllegalArgumentException("No House named \"" + name + "\"");
      }
  }

 
  @GET
  public AbstractMain getMain() {
    checkMain();
    LOG.debug("getMain({})", name);
    return this.main;
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
  public boolean putMain(AbstractMain mainArg) {
    LOG.debug("putMain({})", mainArg);
    AbstractMain oldMain = this.model.putMain(mainArg);
    autoSaveModel();
    return oldMain == null;
  }

 
  @PUT
  public boolean putMain() {
    return putMain(new Main(name));
  }

  
  @POST
  @Path("/rename")
  public boolean renameMain(@QueryParam("newName") String newName) {
    checkMain();
    if (this.model.getMain(newName) != null) {
      throw new IllegalArgumentException("A house named \"" + newName + "\" already exists");
    }
    this.main.setName(newName);
    autoSaveModel();
    return true;
  }

 
  @DELETE
  public boolean removeMain() {
    checkMain();
    this.model.removeMain(this.main);
    autoSaveModel();
    return true;
  }
}