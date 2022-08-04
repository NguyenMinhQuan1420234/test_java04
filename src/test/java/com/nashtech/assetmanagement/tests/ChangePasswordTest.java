package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ChangePasswordTest extends BaseTest {
    LoginPage loginPage;
    ManageUserPage manageUserPage;
    CreateUserPage createUserPage;
    HomePage homePage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;

    @BeforeMethod()
    public void createNewUser() {
        loginPage = new LoginPage();
        manageUserPage = new ManageUserPage();
        createUserPage = new CreateUserPage();
        homePage = new HomePage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();

    }

    @Test(dataProvider = "changePasswordAccountFirstLogin", dataProviderClass = DataProviderUser.class)
    public void changePasswordFirstLoginSuccessfully(JsonObject user) throws InterruptedException {
        loginPage.loginWithAdminAccount();
        homePage.moveToPage("Manage User");
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUser(user);
        manageUserPage.clickDetailFirstUser();
        detailInformationPage.clickClose(detailInformationPage.getUserDetail("Staff Code"));
        String username = detailInformationPage.getUserDetail("Username");
        String accountPassword = username + user.get("password").getAsString();
        homePage.logout();
        alertHandle.closePopup();
        loginPage.login(username, accountPassword);
        alertHandle.closePopup();
        homePage.changePasswordFirstLogin(user.get("newPassword").getAsString());
        alertHandle.waitForAlertMessageDisappear();
        alertHandle.closePopup();
        homePage.logout();
        alertHandle.closePopup();
        loginPage.login(username, user.get("newPassword").getAsString());

        assertThat("verify message login success by new password: ",
                alertHandle.getPopupMessageText(),
                equalTo("Login success!!!")
        );

        alertHandle.closePopup();
    }

    @Test(dataProvider = "changePasswordAccount", dataProviderClass = DataProviderUser.class)
    public void changePasswordSuccessfully(JsonObject user){
        loginPage.login(user.get("username").getAsString(), user.get("oldPassword").getAsString());
        alertHandle.closePopup();
        homePage.changePassword(user.get("oldPassword").getAsString(),user.get("newPassword").getAsString());

        assertThat(
                "verify password changed successfully",
                homePage.getChangePasswordMessage(),
                equalTo("Your password has been changed successfully!")
        );

        homePage.closeMsg();
        homePage.logout();
        alertHandle.closePopup();
        loginPage.login(user.get("username").getAsString(), user.get("newPassword").getAsString());
        alertHandle.closePopup();
        homePage.changePassword(user.get("newPassword").getAsString(),user.get("oldPassword").getAsString());
    }

    @Test
    public void changePasswordUnsuccessfullyOldPassMatchNewPass() {

    }
}
