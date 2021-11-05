package rest.restapi;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import backend.core.Model;
import backend.core.Settings;


public class SettingsResource {

  private static final Logger LOG = LoggerFactory.getLogger(Resource.class);

  private final Model model;


  public SettingsResource(Model model) {
    this.model = model;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Settings getSettings() {
    LOG.debug("getSettings()");
    return this.model.getSettings();
  }


  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void putSettings(Settings settings) {
    LOG.debug("putSettings({})", settings);
    this.model.setSettings(settings);
  }
}
