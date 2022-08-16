package com.nashtech.assetmanagement.tests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nashtech.assetmanagement.constants.APIConstant;
import com.nashtech.assetmanagement.pages.CreateAssetPage;
import com.nashtech.assetmanagement.pages.HomePage;
import com.nashtech.assetmanagement.pages.LoginPage;
import com.nashtech.assetmanagement.pages.ManageAssetPage;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import com.nashtech.assetmanagement.utils.JsonUtil;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.nashtech.assetmanagement.api.*;

import java.util.Collections;
import java.util.List;

public class DeleteUserByAPITest extends BaseTest {
    ManageAssetPage assetPage;
    LoginPage loginPage;
    HomePage homePage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;
    ManageAssetPage manageAssetPage;
    CreateAssetPage createAssetPage;
    UserHelper userHelper = new UserHelper();
    @Test
    public void deleteUserByAPI () {
        Response Token = userHelper.generateToken(System.getProperty("USERNAME"),System.getProperty("PASSWORD"));
        String tokenString  = Token.jsonPath().getString("token");
//        Response search = userHelper.getListBySearchResult(tokenString,"deptrai");
        Response getAllUser = userHelper.getAllUser(tokenString);
//        System.out.println(getAllUser.getBody().prettyPrint());
//        JSONObject jsonObject = new JSONObject(new Gson().toJson((getAllUser.body())));
        String Body = getAllUser.asString();
        JsonArray userList = new JsonParser().parse(Body).getAsJsonArray();
        JsonObject user = new JsonObject();
        for(int i = 0; i < userList.size(); i++) {
            user = userList.get(i).getAsJsonObject();
            if (user.get("fullName").getAsString().contains("quan")) {
                userHelper.deleteUser(tokenString, user.get("staffCode").getAsString());
            }
        }
    }
}
