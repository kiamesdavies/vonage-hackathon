package com.vonage.jamesakinniranye.services;

import com.vonage.jamesakinniranye.services.dtos.UserCreateResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/users")
@RegisterRestClient(configKey = "voiceit-api")
public interface UserService {

    @POST
    UserCreateResponse create();

    @DELETE
    @Path("/{userId}")
    UserCreateResponse delete(@PathParam(value = "userId") String userId);

}
