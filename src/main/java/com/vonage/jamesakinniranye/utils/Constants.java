package com.vonage.jamesakinniranye.utils;

public class Constants {
    public static final String NCCO_VERIFY = """
            {
                "to": [
                    {
                        "type": "phone",
                        "number": "{phoneNumber}"
                    }
                ],
                "from": {
                    "type": "phone",
                    "number": "447441442936"
                },
                "ncco": [
                    {
                        "action": "talk",
                        "text": "There's need to verify your voice, please say Never forget tomorrow is a new day after the tone ",
                        "language": "en-US",
                        "style": 10
                    },
                    {
                        "action": "record",
                        "eventUrl": [
                            "http://voice1.yellowfin.npe:9087/callback/verify/{userId}"
                        ],
                        "endOnKey": "#",
                        "timeOut": 5,
                        "beepStart": true
                    },
                    {
                        "action": "talk",
                        "text": "Thank you, good bye",
                        "language": "en-US",
                        "style": 10
                    }
                ]
            }
            """;
    public static final String NCCO_ENROLL = """
            {
                "to": [
                    {
                        "type": "phone",
                        "number": "{phoneNumber}"
                    }
                ],
                "from": {
                    "type": "phone",
                    "number": "447441442936"
                },
                "ncco": [
                    {
                        "action": "talk",
                        "text": "Welcome to Vonage Hackathon, you will be prompted to say a phrase three times inorder to capture your voice fingerprint",
                        "language": "en-US",
                        "style": 10
                    },
                    {
                        "action": "talk",
                        "text": "After the tone say, <speak><prosody rate='x-slow'>Never forget tomorrow is a new day, <break time=\\"2s\\"/>once again, <break time=\\"2s\\"/> Never forget tomorrow is a new day</prosody></speak>,  then press the hash key",
                        "language": "en-US",
                        "style": 10
                    },
                    {
                        "action": "record",
                        "eventUrl": [
                            "http://voice1.yellowfin.npe:9087/callback/enroll/{userId}"
                        ],
                        "endOnKey": "#",
                        "timeOut": 5,
                        "beepStart": true
                    },
                    {
                        "action": "talk",
                        "text": "Once again, after the tone say never forget tomorrow is a new day then press the hash key",
                        "language": "en-US",
                        "style": 10
                    },
                    {
                        "action": "record",
                        "eventUrl": [
                            "http://voice1.yellowfin.npe:9087/callback/enroll/{userId}"
                        ],
                        "endOnKey": "#",
                        "timeOut": 5
                    },
                    {
                        "action": "talk",
                        "text": "Finally, after the tone say never forget tomorrow is a new day then press the hash key",
                        "language": "en-US",
                        "style": 10
                    },
                    {
                        "action": "record",
                        "eventUrl": [
                            "http://voice1.yellowfin.npe:9087/callback/enroll/{userId}"
                        ],
                        "endOnKey": "#",
                        "timeOut": 5,
                        "beepStart": true
                    },
                    {
                        "action": "talk",
                        "text": "Thanks for sharing your voice, this will be sent to NASA as part of the Unidentified alien among us program",
                        "language": "en-US",
                        "style": 10
                    }
                ]
            }
            """;
    public static final String LANGUAGE = "no-STT";
    public static final String PHRASE = "never forget tomorrow is a new day";
    public static final String API_SERVER = "http://vonage-hackathon.sycliff.com:9087";
    public static final String APPLICATION_ID = "9d1383d4-355b-464c-b9aa-96f8fb6b59b7";
    public static final int LANGUAGE_STYLE = 10;
    public static final String VONAGE_PRIVATE_KEY_CONTENT = """
            -----BEGIN PRIVATE KEY-----
            MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCs7WVRYJmk63MP
            Iq3dY15rREY0Uu1nfIMnni6s2UGb+Hyu0Bpn4WTC2f0mSrDeQ/BG3HwcKzizgiwU
            NZimTALTaR68F+QoQVE56K1L5vopAL/RYWsf71aZ0s8iiAbkn1nkVqiBS9pSN/Uj
            jqeYIeT6B2c6nGoVsiFhdtGJpeYfYyHWxD+n9CMut8SzE0VzBM8MzUYIAF9CBC0s
            lWNfuZx5YbUayRFCq1BDsKjwGCmd4Qw/O6JJfy5GXK/DPU6XdE8Inr1OVyorvZIz
            umADyLVhAhc1XowFNs59Qj72h3xzSNsdeHq+SMX/0vlNj33BcVRIgrirN+42x+8G
            6U50Ft6pAgMBAAECgf9evxbdChskoSOJgE+fFgga4RgyfaDHZGaGGRqA2evqd1tK
            7KUZWujIdksPOo77431w5A7xblltcuiQOE1nXkVYwbUf7ujsM6SRBC62KzB6lPku
            0R+yYyyTW02M6J5QAZumGOMk9HUfc561Z1sBZsmnQpjssk20qsSYOv4o6VWaxqZU
            p37btbQAcSZSeUa0YG8H1XLOnwYPvkEzAzYymvI2mM8ONcY6qtPnlVa6uDuYmpqY
            sAnHq/8qSySptoDh1ZkpSJiphFGGx0qE15Njy8TjyeMJXgi2Ejcl/a96UL02Ltsh
            PZB0w0SV3mamaaGng9acHscUy2UQK4cnuDFC1pECgYEA7xbXBzNr3twj8Er9kEcI
            OfuDSWrNwHh2KmTzC5GbdMpOmcs2yPUck/0ermYDCL6NRHEURXSUOTAZ8D/mRGyu
            Pn5QVlZYshR8pB/k61p/DpA8d3T7zrmx2vn59wP6Rb/ufUzYV3FqY4cfBxW8Ak83
            G8N+4ZTDIyIg6cvL3/oUTpkCgYEAuSiSAJah29p2WetlTTgt6Z+rAqs3IxSBpX+8
            +ElYEY5LuLQluAaV2yVy/SMigXV9I9Y9hoORzoY0ZTKjQ8oVkc2R8YywDJUctapX
            bMkOWVbdTVTL9g9tdbXbdOXMrArsiEldSwSvo0MLBQShtxjVN9xeD1mM17X09F0C
            4/RDapECgYEAhsB/tt5DeNroIoGRgeSW/V8FMrbQeh8qsmAtH6MUU/HF9SY0nXSQ
            g9QQm8iBA0eqhR2aiD7ZX5X9uIu7M2txlWVJ0C9gVBX53eLf+coKfGHMwqMbXPsK
            A9oKCS+jufYf610JPdd9V9ULJsoYKdENxUcc1vUqOj39AOhM4tKm6VkCgYAz4U0/
            gvVpP8YnndBtmY8rxcRJ5CV329gTwj31p79Yc/F9mGR1Msssc0rL6NtTQNNGhlII
            7baeEnZ94HlkliwDBYGeF9iAPgukT01dutwdZjxt3ZOkp1/5OnJ6R6VYk1sBOOU+
            DtsTuMZOqqSRRBECLOniDSpsipxPF/ymWJvHMQKBgECt2qp5c9zG9J9m9oQiTKTf
            44YZL7JLjaJc8awS8jHVSecMpQ0aPSLDylFq2TPB8ZmNGvv7tqK4efLSBkWF+KWU
            ciT5Q5X5zuk9dHXemvhps5dSJKig0Ss0LYWNb3pwE27gTygksXhGaRlfiwM2Dhnd
            lKxWyTYZu8SvTjv805NR
            -----END PRIVATE KEY-----
            """;
    public static final String FROM = "447441442936";
}
