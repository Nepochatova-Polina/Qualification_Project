package org.acme.serverapi;

import org.acme.model.ServerApiDto;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServerApiResource {

  private static final Logger logger = Logger.getLogger(ServerApiResource .class);

  @RestClient ServerApiClient serverApiClient;

  public List<ServerApiDto> getTextResponse() {
    logger.info("Starting...");

    logger.info(serverApiClient.getResponse());
    List<ServerApiDto> serverApiDto = serverApiClient.getResponse();

    logger.info("Response received.");

    return serverApiDto;
  }
}
