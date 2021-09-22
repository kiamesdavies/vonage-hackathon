package com.vonage.jamesakinniranye.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vonage.jamesakinniranye.repository.UserRepository;
import com.vonage.jamesakinniranye.services.VerificationService;
import com.vonage.jamesakinniranye.services.VoiceEnrollmentService;
import com.vonage.jamesakinniranye.services.dtos.VoiceParams;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.vonage.jamesakinniranye.utils.Constants.LANGUAGE;
import static com.vonage.jamesakinniranye.utils.Constants.PHRASE;
import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/callback")
public class CallbackResource {

    final UserRepository userRepository;
    final VerificationService verificationService;
    final VoiceEnrollmentService voiceEnrollmentService;

    private static final Logger LOG = Logger.getLogger(CallbackResource.class);

    @Inject
    public CallbackResource(UserRepository userRepository,
                            @RestClient VerificationService verificationService,
                            @RestClient VoiceEnrollmentService voiceEnrollmentService) {
        this.userRepository = userRepository;
        this.verificationService = verificationService;
        this.voiceEnrollmentService = voiceEnrollmentService;
    }

    @Path("/enroll/{userId}")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_PLAIN)
    public String enrollRecording(@PathParam("userId") String userId, Recording recording) {
        final var response = voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, recording.recording_url()));
        LOG.infov("Enrolled recording {0} for phoneNumber {1}, status {2} ",
                recording.recording_url, userRepository.getPhoneNumber(userId), response.status());
        return format("Enrolled %s with %s", userId, recording.recording_url);

    }

    @Path("/verify/{userId}")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_PLAIN)
    public String verify(@PathParam("userId") String userId, Recording recording) {
        final var response = verificationService.verify(new VoiceParams(userId, LANGUAGE, PHRASE, recording.recording_url()));
        LOG.infov("Verified recording {0} from phoneNumber {1}, match confidence is {2}, text confidence is {3}, and  status {4}",
                recording.recording_url, userRepository.getPhoneNumber(userId), response.confidence(), response.textConfidence(), response.status());
        return format("Verified %s with %s", userId, recording.recording_url);
    }


    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    record Recording(@XmlElement String recording_url) {
    }
}
