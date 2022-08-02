package com.nashtech.assetmanagement.dataProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.utils.JsonUtil;
import org.testng.annotations.DataProvider;

import static com.nashtech.assetmanagement.utils.JsonUtil.readJsonFile;

public class DataProviderUser {
    static JsonObject userList = readJsonFile("src/test/resources/testdata/user_test_data.json");

    @DataProvider(name = "createUserWithAdminAccount")
    public static Object[][] getAccountInformationWithValidPassword() {
        JsonArray userData = userList.getAsJsonArray("validUserData");
        JsonObject user1 = userData.get(0).getAsJsonObject();
        JsonObject user2 = userData.get(1).getAsJsonObject();
        return new Object[][]{
                {user1}, {user2}
        };
    }

    @DataProvider(name = "createUserAccount")
    public static Object[][] getAccount() {
        JsonArray userData = userList.getAsJsonArray("validUserData");
        JsonObject user = userData.get(0).getAsJsonObject();
        return new Object[][]{
                {user}
        };
    }

    @DataProvider(name = "changePasswordAccount")
    public static Object[][] changePasswordAccount() {
        JsonObject user = userList.getAsJsonObject("changePassUserData");
        return new Object[][]{
                {user}
        };
    }
}
