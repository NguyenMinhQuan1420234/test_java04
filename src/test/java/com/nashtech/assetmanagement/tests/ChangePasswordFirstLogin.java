package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordFirstLogin extends BaseTest {
    LoginPage loginPage;
    ManageUserPage manageUserPage;
    CreateUserPage createUserPage;
    NavigationPage navigationPage;
    DetailInformationPage detailInformationPage;

    @BeforeMethod()
    public void createNewUser() {
        loginPage = new LoginPage();
        manageUserPage = new ManageUserPage();
        createUserPage = new CreateUserPage();
        navigationPage = new NavigationPage();
        detailInformationPage = new DetailInformationPage();

        loginPage.loginWithAdminAccount();
    }

    @Test(dataProvider = "changePasswordAccount", dataProviderClass = DataProviderUser.class)
    public void changePasswordFirstLoginSuccessfully(JsonObject user) {
        navigationPage.moveToPage("Manage User");
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUser(user);
        manageUserPage.clickDetailFirstUser();
        detailInformationPage.clickCloseFormButton(detailInformationPage.getTextOfUserLabel("Staff Code"));
        String username = detailInformationPage.getTextOfUserLabel("Username");
        navigationPage.logout();
        loginPage.loginWithCustomAccount(username, user.get("password").getAsString());
//        navigationPage.changePassword();
    }
}
