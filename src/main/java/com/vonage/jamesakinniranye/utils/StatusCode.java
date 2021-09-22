package com.vonage.jamesakinniranye.utils;

public class StatusCode {

    public static boolean isOk(int status){
        return status == 200 || status == 201;
    }
}
