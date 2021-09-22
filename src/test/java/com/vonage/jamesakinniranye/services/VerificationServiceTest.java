package com.vonage.jamesakinniranye.services;

import com.vonage.jamesakinniranye.repository.UserRepository;
import com.vonage.jamesakinniranye.services.dtos.VoiceParams;
import com.vonage.jamesakinniranye.stubs.VoiceItServiceMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.vonage.jamesakinniranye.utils.Constants.LANGUAGE;
import static com.vonage.jamesakinniranye.utils.Constants.PHRASE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
@QuarkusTestResource(VoiceItServiceMockServer.class)
public class VerificationServiceTest {
    @Inject
    private UserRepository userRepository;
    @Inject
    @RestClient
    private VoiceEnrollmentService voiceEnrollmentService;
    @Inject
    @RestClient
    private VerificationService verificationService;

    private String userId;

    @Test
    public void shouldBeAbleToVerify(){
        final var response = verificationService.verify(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd5.mp3"));
        assertThat(response.confidence(), greaterThanOrEqualTo(75d));
    }

    @BeforeEach
    void createUser(){
        userId = userRepository.createUser("075657257868").userId();
        voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd1.mp3"));
        voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd2.mp3"));
        voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd3.mp3"));
    }

    @AfterEach
    void cleanUp(){
        userRepository.deleteUser(userId);
    }
}
