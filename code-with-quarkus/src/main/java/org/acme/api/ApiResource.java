package org.acme.api;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import org.acme.model.ServerApiDto;
import org.acme.serverapi.ServerApiService;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
@Path("/api/v1")
public class ApiResource {

  private static final Logger logger = Logger.getLogger(ApiResource.class);

  @Inject
  ServerApiService serverApiService;

  @Inject
  @Location("hello.html")
  Template hello;

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getTextResponse() {
    logger.info("Starting...");
    List<ServerApiDto> serverApiDto = serverApiService.getFromServer();
    return hello.data("student",serverApiDto.get(0));
  }
}
