package com.vonage.jamesakinniranye.services;

import com.vonage.jamesakinniranye.repository.UserRepository;
import com.vonage.jamesakinniranye.services.dtos.VoiceParams;
import com.vonage.jamesakinniranye.stubs.VoiceItServiceMockServer;
import com.vonage.jamesakinniranye.utils.Constants;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import static com.vonage.jamesakinniranye.utils.Constants.LANGUAGE;
import static com.vonage.jamesakinniranye.utils.Constants.PHRASE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusTestResource(VoiceItServiceMockServer.class)
public class VoiceEnrollmentServiceTest {

    @Inject
    private UserRepository userRepository;
    @Inject
    @RestClient
    private VoiceEnrollmentService voiceEnrollmentService;

    private String userId;

    @Test
    public void shouldBeAbleToEnroll(){
        final var enroll = voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/etuo38mzqiu8xsh7/rec4.mp3"));
        assertThat(enroll.status(), equalTo(201));

    }

    @BeforeEach
    void createUser(){
        userId = userRepository.createUser("075657257868").userId();
    }

    @AfterEach
    void cleanUp(){
        userRepository.deleteUser(userId);
    }
}
