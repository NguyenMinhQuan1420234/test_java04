package com.nashtech.assetmanagement.dataProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.json.Json;
import org.testng.annotations.DataProvider;

import static com.nashtech.assetmanagement.utils.JsonUtil.readJsonFile;

public class DataProviderAsset {
    static JsonObject assetData = readJsonFile("src/test/resources/testdata/asset_test_data.json");

    @DataProvider(name = "assetData")
    public static Object[][] assetData() {
        JsonObject asset= assetData.getAsJsonObject("data1");
        String name = asset.get("name").getAsString();
        String category = asset.get("category").getAsString();
        String specification = asset.get("specification").getAsString();
        String installedDate = asset.get("installedDate").getAsString();
        String state = asset.get("state").getAsString();
        return new Object[][]{
                {name,category,specification,installedDate,state}
        };
    }
    @DataProvider(name = "assetNewCategoryData")
    public static Object[][] assetNewCategoryData() {
        JsonObject asset= assetData.getAsJsonObject("data2");
        String newCategoryName = asset.get("newCategoryName").getAsString();
        String newCategoryPrefix = asset.get("newCategoryPrefix").getAsString();
        return new Object[][]{
                {newCategoryName,newCategoryPrefix}
        };
    }
}
