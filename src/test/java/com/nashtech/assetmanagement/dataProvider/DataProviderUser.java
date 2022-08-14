package com.nashtech.assetmanagement.dataProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.utils.JsonUtil;
import org.openqa.selenium.json.Json;
import org.testng.annotations.DataProvider;

import static com.nashtech.assetmanagement.utils.JsonUtil.readJsonFile;

public class DataProviderUser {
    static JsonObject userList = readJsonFile("src/test/resources/testdata/user_test_data.json");
    static JsonObject editData = readJsonFile("src/test/resources/testdata/edit_user_data.json");

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

    @DataProvider(name = "changePasswordAccountFirstLogin")
    public static Object[][] changePasswordAccountFirstLogin() {
        JsonObject user = userList.getAsJsonObject("changePassUserData");
        return new Object[][]{
                {user}
        };
    }

    @DataProvider(name = "changePasswordAccount")
    public static Object[][] changePasswordAccount() {
        JsonObject admin = userList.getAsJsonObject("Admin");
        JsonObject staff = userList.getAsJsonObject("Staff");
        return new Object[][]{
                {admin}, {staff}
        };
    }
    @DataProvider(name = "editUsetData")
    public static Object[][] editUserData() {
        String dateOfBirth = editData.get("dateOfBirth").getAsString();
        String gender = editData.get("gender").getAsString();
        String joinedDate = editData.get("joinedDate").getAsString();
        String type = editData.get("type").getAsString();

        return new Object[][]{
                {dateOfBirth, gender, joinedDate, type}
        };
    }


}
