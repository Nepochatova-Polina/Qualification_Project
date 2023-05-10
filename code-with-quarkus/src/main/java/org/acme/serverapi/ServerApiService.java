package org.acme.serverapi;

import org.acme.model.ServerApiDto;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ServerApiService {

  private static final Logger logger = Logger.getLogger(ServerApiService.class);

  @Inject
  ServerApiResource serverApiResource;

  public List<ServerApiDto> getFromServer() {
    logger.info("trying server...");

    List<ServerApiDto> response = serverApiResource.getTextResponse();
    String name = response.get(1).getFirstname();

    logger.info(name);

    return response;
  }
}
