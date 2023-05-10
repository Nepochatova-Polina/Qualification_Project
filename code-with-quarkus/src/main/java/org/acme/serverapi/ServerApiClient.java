package org.acme.serverapi;

import io.smallrye.mutiny.Multi;
import org.acme.model.ServerApiDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import java.util.List;

@RegisterRestClient(configKey = "student-api")
public interface ServerApiClient {

  @GET
  List<ServerApiDto> getResponse();
}
