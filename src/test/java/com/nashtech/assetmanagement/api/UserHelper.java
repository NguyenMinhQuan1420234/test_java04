package com.nashtech.assetmanagement.api;

import com.nashtech.assetmanagement.constants.APIConstant;
import com.nashtech.assetmanagement.constants.UrlConstants;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserHelper extends RequestHelper {
    private String prefixUrl = APIConstant.BASE_API_PAGE;

    public Response generateToken(String userName, String password) {
        String url = prefixUrl + APIConstant.GENERATE_TOKEN_ENDPOINT;
        JSONObject body = new JSONObject();
        body.put("username", userName);
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

    public Response deleteUser(String Token, String staffCode) {
        String url = prefixUrl + APIConstant.DELETE_USER_ENDPOINT + staffCode;
        JSONObject body = new JSONObject();
        Map<String,String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + Token);
        Headers headers = createHeaders(map);
        Response response = sendRequest(
                APIConstant.RequestType.PATCH,
                url,
                headers,
                body);
        return response;
    }

    public Response getAllUser(String Token) {
        String url = prefixUrl + APIConstant.GET_ALL_USER_ENDPOINT;
        Map<String,String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + Token);
        Headers headers = createHeaders(map);
        JSONObject body = new JSONObject();
        Response response = sendRequest(
                APIConstant.RequestType.GET,
                url,
                headers,
                body.toString());
        return response;
    }
    public Response getListBySearchResult(String token, String searchCriteria) {
        String url = prefixUrl + APIConstant.SEARCH_USER_ENDPOINT + searchCriteria;
        System.out.println(url);
        Map<String,String> map = new HashMap<>();
        map.put("Authorization", "Bearer" + token);
        Headers headers = createHeaders(map);
        JSONObject body = new JSONObject();
        Response response = sendRequest(
                APIConstant.RequestType.GET,
                url,
                headers,
                body.toString());
        return response;
    }
}
