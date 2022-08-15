package com.nashtech.assetmanagement.api;

import com.nashtech.assetmanagement.constants.APIConstant;
import com.nashtech.assetmanagement.constants.UrlConstants;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserHelper extends RequestHelper {
    private String prefixUrl = UrlConstants.BASE_PAGE;

    public Response generateToken(String userName, String password) {
        String url = prefixUrl + APIConstant.GENERATE_TOKEN_ENDPOINT;
        JSONObject body = new JSONObject();
        body.put("userName", userName);
        body.put("password", password);
        Map<String,String> map = new HashMap<>();
        Headers headers = createHeaders(map);
        Response response = sendRequest(
                APIConstant.RequestType.POST,
                url,
                headers,
                body.toString());
        return response;
    }
}
