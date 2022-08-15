package com.nashtech.assetmanagement.constants;

public class APIConstant {
    public enum RequestType {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH
    }

    public static final String CREATE_ASSET = "/api/assets";
    public static final String GENERATE_TOKEN_ENDPOINT = "/api/auth/login";

}
