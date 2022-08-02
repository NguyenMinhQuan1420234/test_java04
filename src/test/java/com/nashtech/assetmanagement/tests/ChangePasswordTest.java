package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.AlertHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ChangePasswordTest extends BaseTest {
    LoginPage loginPage;
    ManageUserPage manageUserPage;
    CreateUserPage createUserPage;
    NavigationPage navigationPage;
    DetailInformationPage detailInformationPage;
    AlertHandle alertHandle;

    @BeforeMethod()
    public void createNewUser() {
        loginPage = new LoginPage();
        manageUserPage = new ManageUserPage();
        createUserPage = new CreateUserPage();
        navigationPage = new NavigationPage();
        detailInformationPage = new DetailInformationPage();
        alertHandle = new AlertHandle();

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
        String accountPassword = username + user.get("password").getAsString();
        navigationPage.logout();
        loginPage.loginWithCustomAccount(username, accountPassword);
        navigationPage.changePasswordFirstLogin(user.get("newPassword").getAsString());

        assertThat("verify message success: ",
                alertHandle.getPopupMessageText("Change Password success!!!"),
                equalTo("Change Password success!!!")
        );

        alertHandle.waitForAlertMessageDisappear("Change Password success!!!");
        navigationPage.logout();
        loginPage.loginWithCustomAccount(username, user.get("newPassword").getAsString());


        assertThat("verify message login success by new password: ",
                alertHandle.getPopupMessageText("Login success!!!"),
                equalTo("Login success!!!")
        );
    }

    @Test
    public void changePasswordSuccessfully(){

    }

}
