package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import com.nashtech.assetmanagement.utils.RandomStringUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void precondition() {
        loginPage = new LoginPage();
        manageUserPage = new ManageUserPage();
        createUserPage = new CreateUserPage();
        homePage = new HomePage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();

    }

    @Test(dataProvider = "changePasswordAccountFirstLogin", dataProviderClass = DataProviderUser.class)
    public void changePasswordFirstLoginSuccessfully(JsonObject user) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUserDefault(user);
        alertHandle.closeAlert();
        manageUserPage.clickDetailFirstUser();
        detailInformationPage.clickClose();
        String username = detailInformationPage.getDetailInformationValue("Username");
        String accountPassword = username + user.get("password").getAsString();
        homePage.logout();
        alertHandle.closeAlert();
        loginPage.login(username, accountPassword);
        alertHandle.closeAlert();
        homePage.changePasswordFirstLogin(user.get("newPassword").getAsString());
        alertHandle.closeAlert();
        homePage.logout();
        alertHandle.waitForAlertMessageDisappear();
        loginPage.login(username, user.get("newPassword").getAsString());

        assertThat("verify message login success by new password: ",
                alertHandle.getAlertMessageText(),
                equalTo("Login success!!!")
        );

    }

    @Test(dataProvider = "changePasswordAccount", dataProviderClass = DataProviderUser.class)
    public void changePasswordSuccessfully(JsonObject user){
        homePage.waitLoadingScreen();
        loginPage.login(user.get("username").getAsString(), user.get("oldPassword").getAsString());
        alertHandle.waitForAlertMessageDisappear();
        homePage.changePassword(user.get("oldPassword").getAsString(),user.get("newPassword").getAsString());

        assertThat(
                "verify password changed successfully",
                homePage.getChangePasswordMessage(),
                equalTo("Your password has been changed successfully!")
        );

        homePage.closeMsg();
        homePage.logout();
        alertHandle.closeAlert();
        loginPage.login(user.get("username").getAsString(), user.get("newPassword").getAsString());
        alertHandle.closeAlert();
        homePage.changePassword(user.get("newPassword").getAsString(),user.get("oldPassword").getAsString());
    }

    @Test
    public void changePasswordUnsuccessfullyOldPassNotMatch() {
        loginPage.loginWithDefaultAccount();
        alertHandle.waitForAlertMessageDisappear();
        homePage.changePassword(RandomStringUtil.randomPasword(), System.getProperty("USERNAME"));

        assertThat(
                "verify wrong old password ",
                homePage.getTextWrongOldPassword(),
                equalTo("Password is incorrect")
        );
    }

    @Test
    public void changePasswordCancel() {
        loginPage.loginWithDefaultAccount();
        alertHandle.waitForAlertMessageDisappear();
        homePage.openChangePassword();
        homePage.clickCancelChangePassword();

        assertThat(
                "verify modal changepassword has been closed: ",
                homePage.isPopupClosed(),
                equalTo(true)
        );
    }
}
