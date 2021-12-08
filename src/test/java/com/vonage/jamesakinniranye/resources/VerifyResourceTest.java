package com.vonage.jamesakinniranye.resources;

import com.vonage.jamesakinniranye.repository.UserRepository;
import com.vonage.jamesakinniranye.stubs.VoiceItServiceMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@QuarkusTestResource(VoiceItServiceMockServer.class)
public class VerifyResourceTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void shouldBeAbleToEnroll() {
        given().when()
                .post("/verify/enroll/{phoneNumber}", "447565257868")
                .then()
                .statusCode(200)
                .body(containsString("usr_feb6d1fcd80448628db8ec6a7ddb6322"));
    }

    @Test
    public void shouldBeAbleToVerify() {
        userRepository.createUser("447565257868");
        given().when()
                .post("/verify/{phoneNumber}", "447565257868")
                .then()
                .statusCode(200)
                .body(containsString("success"));
    }


}
