package com.vonage.jamesakinniranye.services;

import com.vonage.jamesakinniranye.services.dtos.VoiceEnrollmentResponse;
import com.vonage.jamesakinniranye.services.dtos.VoiceParams;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/enrollments/voice/byUrl")
@RegisterRestClient(configKey = "voiceit-api")
public interface VoiceEnrollmentService {

    @POST
    VoiceEnrollmentResponse enroll(VoiceParams params);
}
