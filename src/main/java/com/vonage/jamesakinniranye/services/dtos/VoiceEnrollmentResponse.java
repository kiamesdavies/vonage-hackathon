package com.vonage.jamesakinniranye.services.dtos;

public record VoiceEnrollmentResponse(String message,
                                      String contentLanguage,
                                      Integer id,
                                      Integer status,
                                      String text,
                                      Double textConfidence,
                                      Long createdAt,
                                      String timeTaken,
                                      String responseCode) {


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VoiceEnrollmentResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append(", contentLanguage='").append(contentLanguage).append('\'');
        sb.append(", id=").append(id);
        sb.append(", status=").append(status);
        sb.append(", text='").append(text).append('\'');
        sb.append(", textConfidence=").append(textConfidence);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", timeTaken='").append(timeTaken).append('\'');
        sb.append(", responseCode='").append(responseCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
