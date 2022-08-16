package com.nashtech.assetmanagement.constants;

public class APIConstant {
    public enum RequestType {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH
    }
    public static final String BASE_API_PAGE = "https://backend05.azurewebsites.net";

    public static final String CREATE_ASSET = "/api/assets";
    public static final String GENERATE_TOKEN_ENDPOINT = "/api/auth/login";
    public static final String DELETE_USER_ENDPOINT = "/api/user/disable/";
    public static final String GET_ALL_USER_ENDPOINT = "/api/user/getAll";
    public static final String SEARCH_USER_ENDPOINT = "/api/user/search/";
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTY2MDYzNTU4OSwiZXhwIjoxNjYxMjQwMzg5fQ.gfmAYRMTvwTvbEiMIk-7Mlwae7h17-7ILRVHmZJxYkA";

}
