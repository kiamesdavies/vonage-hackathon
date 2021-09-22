package com.vonage.jamesakinniranye.services.dtos;

public record VoiceParams(String userId,
                          String contentLanguage,
                          String phrase,
                          String fileUrl) {
}
