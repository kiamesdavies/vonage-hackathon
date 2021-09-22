package com.vonage.jamesakinniranye.services;

import com.vonage.client.VonageClient;
import com.vonage.client.voice.Call;
import com.vonage.client.voice.CallEvent;
import com.vonage.client.voice.VoiceClient;
import com.vonage.client.voice.ncco.Action;
import com.vonage.client.voice.ncco.Ncco;
import com.vonage.client.voice.ncco.RecordAction;
import com.vonage.client.voice.ncco.TalkAction;

import javax.enterprise.context.ApplicationScoped;

import static com.vonage.client.voice.TextToSpeechLanguage.AMERICAN_ENGLISH;
import static com.vonage.jamesakinniranye.utils.Constants.*;
import static java.lang.String.format;

@ApplicationScoped
public class VonageService {

    private static final VoiceClient VOICE_CLIENT = VonageClient.builder()
            .applicationId(APPLICATION_ID)
            .privateKeyContents(VONAGE_PRIVATE_KEY_CONTENT)
            .build()
            .getVoiceClient();


    public CallEvent verifyCall(String phoneNumber, String userId) {
        final var call = new Call(phoneNumber, FROM, new Ncco(
                talk("There's need to verify your voice, please say Never forget tomorrow is a new day after the tone"),
                record(format("%s/callback/verify/%s", API_SERVER, userId)),
                talk("Thank you, good bye")
        ));
        return VOICE_CLIENT.createCall(call);
    }

    public CallEvent enrollCall(String phoneNumber, String userId) {
        final var url = format("%s/callback/enroll/%s", API_SERVER, userId);
        final var call = new Call(phoneNumber, FROM, new Ncco(
                talk("Welcome to Vonage Hackathon, you will be prompted to say a phrase three times inorder to capture your voice fingerprint"),
                talk("After the tone say, <speak><prosody rate='x-slow'>Never forget tomorrow is a new day, <break time=\"2s\"/>once again, <break time=\"2s\"/> Never forget tomorrow is a new day</prosody></speak>,  then press the hash key"),
                record(url),
                talk("Once again, after the tone say never forget tomorrow is a new day then press the hash key"),
                record(url),
                talk("Finally, after the tone say never forget tomorrow is a new day then press the hash key"),
                record(url),
                talk("Thanks for sharing your voice, this will be sent to NASA as part of the Unidentified alien among us program. Bye bye")
        ));
        return VOICE_CLIENT.createCall(call);
    }

    private static Action talk(String text) {
        return TalkAction.builder(text).language(AMERICAN_ENGLISH).style(LANGUAGE_STYLE).build();
    }

    private static Action record(String url) {
        return RecordAction.builder().eventUrl(url).endOnKey('#').timeOut(5).beepStart(true).build();
    }

}
