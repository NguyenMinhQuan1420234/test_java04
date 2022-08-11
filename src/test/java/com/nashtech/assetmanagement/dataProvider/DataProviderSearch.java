package com.nashtech.assetmanagement.dataProvider;

import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

import static com.nashtech.assetmanagement.utils.JsonUtil.readJsonFile;

public class DataProviderSearch {
    static JsonObject viewAdminSortData = readJsonFile("src/test/resources/testdata/search_test_data.json");

    @DataProvider(name = "sortHeaderUserData")
    public static Object[][] sortHeaderData() {
        String sortStaff = viewAdminSortData.getAsJsonObject("sortByHeader").get("staff").getAsString();
        String sortName = viewAdminSortData.getAsJsonObject("sortByHeader").get("name").getAsString();
        String sortJoinedDate = viewAdminSortData.getAsJsonObject("sortByHeader").get("joinedDate").getAsString();
        String sortType = viewAdminSortData.getAsJsonObject("sortByHeader").get("type").getAsString();
        String ascend = viewAdminSortData.getAsJsonObject("sortType").get("type1").getAsString();
        String descend = viewAdminSortData.getAsJsonObject("sortType").get("type2").getAsString();
        return new Object[][]{
                {sortStaff, ascend}, {sortStaff, descend},
                {sortName, ascend}, {sortName, descend},
                {sortJoinedDate, ascend}, {sortJoinedDate, descend},
                {sortType, ascend}, {sortType, descend}
        };
    }
    @DataProvider(name = "filterUserData")
    public static Object[][] sortFilterData() {
        String staff = viewAdminSortData.getAsJsonObject("filter").get("staff").getAsString();
        String admin = viewAdminSortData.getAsJsonObject("filter").get("admin").getAsString();
        return new Object[][]{
                {staff}, {admin}
        };
    }
    @DataProvider(name = "searchData")
    public static Object[][] searchData() {
        String staff = viewAdminSortData.getAsJsonObject("searchData").get("staffCode").getAsString();
        String name = viewAdminSortData.getAsJsonObject("searchData").get("firstName").getAsString();
        String type1 = viewAdminSortData.getAsJsonObject("searchData").get("searchType1").getAsString();
        String type2 = viewAdminSortData.getAsJsonObject("searchData").get("searchType2").getAsString();
        return new Object[][]{
                {staff, type1}, {name, type2}
        };
    }
    @DataProvider(name = "searchDataForDisableTest")
    public static Object[][] searchDataForDisableTest() {
        String name1 = viewAdminSortData.getAsJsonObject("assignedUser").get("firstName1").getAsString();
        String name2 = viewAdminSortData.getAsJsonObject("assignedUser").get("firstName2").getAsString();
        return new Object[][]{
                {name1}, {name2}
        };
    }
}
