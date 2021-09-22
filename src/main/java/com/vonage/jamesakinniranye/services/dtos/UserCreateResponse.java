package com.vonage.jamesakinniranye.services.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserCreateResponse(String message,
                                 Integer status,
                                 String timeTaken,
                                 Long createdAt,
                                 String userId,
                                 String responseCode) {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserCreateResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append(", status=").append(status);
        sb.append(", timeTaken='").append(timeTaken).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", responseCode='").append(responseCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}