package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DisableUserTest extends BaseTest {
    CreateUserPage createUserPage;
    ManageUserPage manageUserPage;
    LoginPage loginPage;
    HomePage homePage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;

    @BeforeMethod
    public void precondition() {
        createUserPage = new CreateUserPage();
        manageUserPage = new ManageUserPage();
        loginPage = new LoginPage();
        homePage = new HomePage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();

        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void adminDisableUserSuccessfully(JsonObject user) {
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUser(user);
        manageUserPage.clickCreatedUserToDisable();

    }
}
