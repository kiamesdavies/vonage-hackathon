package com.vonage.jamesakinniranye.services;

import com.vonage.client.voice.CallStatus;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class VonageServiceTest {

    @Inject
    private VonageService vonageService;

    @Test
    public void shouldBeAbleToVerifyCall() {
        final var callEvent = vonageService.verifyCall("4475657257869", "usr_feb6d1fcd80448628db8ec6a7ddb6322");
        assertThat(callEvent.getStatus(), equalTo(CallStatus.STARTED));
    }

    @Test
    public void shouldBeAbleToEnrollCall() {
        final var callEvent = vonageService.enrollCall("4475657257869", "usr_feb6d1fcd80448628db8ec6a7ddb6322");
        assertThat(callEvent.getStatus(), equalTo(CallStatus.STARTED));
    }
}
