package com.vonage.jamesakinniranye.repository;

import com.vonage.jamesakinniranye.stubs.VoiceItServiceMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(VoiceItServiceMockServer.class)
public class UserRepositoryTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void testUserFlow(){
        var user = userRepository.createUser("075657257868");
        assertThat(user.status(), anyOf(equalTo(200), equalTo(201)));
        assertThat(userRepository.getUser("075657257868"), notNullValue());
        user = userRepository.deleteUser("075657257868");
        assertThat(user.status(), anyOf(equalTo(200), equalTo(201)));
        assertThat(userRepository.getUser("075657257868"), nullValue());
    }
}
