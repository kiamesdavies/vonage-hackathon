package com.vonage.jamesakinniranye.resources;

import com.vonage.jamesakinniranye.repository.UserRepository;
import com.vonage.jamesakinniranye.services.VonageService;
import com.vonage.jamesakinniranye.services.dtos.UserCreateResponse;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Map;

import static com.vonage.jamesakinniranye.utils.StatusCode.isOk;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/verify")
public class VerifyResource {

    final UserRepository userRepository;
    final VonageService vonageService;

    @Inject
    public VerifyResource(UserRepository userRepository,
                          VonageService vonageService) {
        this.userRepository = userRepository;
        this.vonageService = vonageService;
    }

    @POST
    @Path("/enroll/{phoneNumber}")
    @Produces(APPLICATION_JSON)
    public UserCreateResponse enroll(@PathParam("phoneNumber") String phoneNumber) {
        final var user = userRepository.createUser(phoneNumber);
        if (isOk(user.status())) {
            vonageService.enrollCall(phoneNumber, user.userId());
        }
        return user;
    }

    @POST
    @Path("/{phoneNumber}")
    @Produces(APPLICATION_JSON)
    public Map<String, String> verify(@PathParam("phoneNumber") String phoneNumber) {
        final var userId = userRepository.getUser(phoneNumber);
        if (userId != null) {
            vonageService.verifyCall(phoneNumber, userId);
            return Map.of("status", "success");
        }
        return Map.of("status", "cant find user");
    }

}
