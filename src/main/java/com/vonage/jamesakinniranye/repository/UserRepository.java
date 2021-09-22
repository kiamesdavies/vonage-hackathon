package com.vonage.jamesakinniranye.repository;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.vonage.jamesakinniranye.services.UserService;
import com.vonage.jamesakinniranye.services.dtos.UserCreateResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static com.vonage.jamesakinniranye.utils.StatusCode.isOk;

@ApplicationScoped
public class UserRepository {

    private final UserService userService;

    private final BiMap<String, String> users;

    @Inject
    public UserRepository(@RestClient UserService userService) {
        this.userService = userService;
        users = HashBiMap.create();
    }

    public UserCreateResponse createUser(String phoneNumber) {
        synchronized (phoneNumber.intern()){
            if (users.containsKey(phoneNumber)) {
                return new UserCreateResponse("User already exists", 400, "0", 0L, null, "FAILED");
            }
            final var response = userService.create();
            if (isOk(response.status()) && !users.containsValue(response.userId()) ) {
                users.put(phoneNumber, response.userId());
            }
            return response;
        }
    }

    public UserCreateResponse deleteUser(String phoneNumber) {
        synchronized (phoneNumber.intern()){
            if (!users.containsKey(phoneNumber)) {
                return new UserCreateResponse("User doesn't exist", 400, "0", 0L, null, "FAILED");
            }
            final var response = userService.delete(users.get(phoneNumber));
            if (isOk(response.status())) {
                users.remove(phoneNumber);
            }
            return response;
        }
    }

    public String getUser(String phoneNumber) {
        return users.get(phoneNumber);
    }

    public String getPhoneNumber(String userId) {
        return users.inverse().get(userId);
    }
}
