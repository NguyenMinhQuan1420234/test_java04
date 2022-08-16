package com.nashtech.assetmanagement.api;

import com.nashtech.assetmanagement.constants.APIConstant;
import com.nashtech.assetmanagement.constants.UrlConstants;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AssetHelper extends RequestHelper {
    private String prefixUrl = APIConstant.BASE_API_PAGE;

    public Response createNewAsset(String userToken, String category, String installedDate, String name, String specification, String state) {
        String url = prefixUrl + APIConstant.CREATE_ASSET;
        JSONObject body = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + userToken);
        Headers headers = createHeaders(map);
        body.put("category", category);
        body.put("installedDate", installedDate);
        body.put("name", name);
        body.put("specification", specification);
        body.put("state", state);
        Response response = sendRequest(
                APIConstant.RequestType.POST,
                url,
                headers,
                body.toString());
        return response;
    }

}
