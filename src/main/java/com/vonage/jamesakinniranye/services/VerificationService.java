package com.vonage.jamesakinniranye.services;

import com.vonage.jamesakinniranye.services.dtos.VerificationResponse;
import com.vonage.jamesakinniranye.services.dtos.VoiceParams;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/verification/voice/byUrl")
@RegisterRestClient(configKey = "voiceit-api")
public interface VerificationService {

    @POST
    VerificationResponse verify(VoiceParams params);
}
