package com.vonage.jamesakinniranye.services.dtos;

public record VerificationResponse(Integer status,
                                   String text,
                                   Double textConfidence,
                                   Double confidence,
                                   String timeTaken,
                                   String responseCode) {


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VerificationResponse{");
        sb.append(", status=").append(status);
        sb.append(", textConfidence=").append(confidence);
        sb.append(", textConfidence=").append(textConfidence);
        sb.append(", timeTaken='").append(timeTaken).append('\'');
        sb.append(", responseCode='").append(responseCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
