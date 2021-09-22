package com.vonage.jamesakinniranye.resources;

import com.vonage.jamesakinniranye.repository.UserRepository;
import com.vonage.jamesakinniranye.services.VerificationService;
import com.vonage.jamesakinniranye.services.VoiceEnrollmentService;
import com.vonage.jamesakinniranye.services.dtos.VoiceParams;
import com.vonage.jamesakinniranye.stubs.VoiceItServiceMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.vonage.jamesakinniranye.utils.Constants.LANGUAGE;
import static com.vonage.jamesakinniranye.utils.Constants.PHRASE;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(VoiceItServiceMockServer.class)
public class CallbackResourceTest {

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
    public void shouldBeAbleToEnrollWithRecording() {
        given().when()
                .contentType(ContentType.JSON)
                .body(new CallbackResource.Recording("https://filebin.net/yyv16td59gchc8cj/rd5.mp3"))
                .post("/callback/enroll/{userId}", "usr_feb6d1fcd80448628db8ec6a7ddb6322")
                .then()
                .statusCode(200)
                .body(is(format("Enrolled %s with %s", "usr_feb6d1fcd80448628db8ec6a7ddb6322","https://filebin.net/yyv16td59gchc8cj/rd5.mp3")));
    }

    @Test
    public void shouldBeAbleToVerifyWithRecording() {
        given().when()
                .contentType(ContentType.JSON)
                .body(new CallbackResource.Recording("https://filebin.net/yyv16td59gchc8cj/rd5.mp3"))
                .post("/callback/verify/{userId}", "usr_feb6d1fcd80448628db8ec6a7ddb6322")
                .then()
                .statusCode(200)
                .body(is(format("Verified %s with %s", "usr_feb6d1fcd80448628db8ec6a7ddb6322","https://filebin.net/yyv16td59gchc8cj/rd5.mp3")));
    }

    @BeforeEach
    void createUser() {
        userId = userRepository.createUser("075657257868").userId();
        voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd1.mp3"));
        voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd2.mp3"));
        voiceEnrollmentService.enroll(new VoiceParams(userId, LANGUAGE, PHRASE, "https://filebin.net/yyv16td59gchc8cj/rd3.mp3"));
    }

    @AfterEach
    void cleanUp() {
        userRepository.deleteUser(userId);
    }
}
