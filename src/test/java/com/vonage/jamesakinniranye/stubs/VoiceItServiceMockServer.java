package com.vonage.jamesakinniranye.stubs;
import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
public class VoiceItServiceMockServer implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();

        stubFor(any(urlMatching("/users.*")).willReturn(okJson("""
                {
                    "message": "Created user with userId : usr_feb6d1fcd80448628db8ec6a7ddb6322",
                    "status": 201,
                    "timeTaken": "0.055s",
                    "createdAt": 1487026658000,
                    "userId": "usr_feb6d1fcd80448628db8ec6a7ddb6322",
                    "responseCode":"SUCC"
                }
                """)));

        stubFor(any(urlEqualTo("/verification/voice/byUrl")).willReturn(okJson("""
                {
                    "message": "Successfully verified voice for user with userId : usr_feb6d1fcd80448628db8ec6a7ddb6322",
                    "status": 200,
                    "confidence": 94.0,
                    "text": "never forget tomorrow is a new day",
                    "textConfidence": 100,
                    "timeTaken": "6.055s",
                    "responseCode":"SUCC"
                }
                """)));

        stubFor(any(urlEqualTo("/enrollments/voice/byUrl")).willReturn(okJson("""
                {
                 "message": "Successfully enrolled voice for user with userId : usr_feb6d1fcd80448628db8ec6a7ddb6322",
                 "contentLanguage": "en-US",
                 "id": 57,
                 "status": 201,
                 "text": "never forget tomorrow is a new day",
                 "textConfidence": 100.00,
                 "createdAt": 1487026658000,
                 "timeTaken": "7.718s",
                 "responseCode" : "SUCC"
                 }
                """)));

        return Collections.singletonMap("voiceit-api/mp-rest/url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
