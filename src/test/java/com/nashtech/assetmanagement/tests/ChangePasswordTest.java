package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ChangePasswordTest extends BaseTest {
    LoginPage loginPage;
    ManageUserPage manageUserPage;
    CreateUserPage createUserPage;
    HomePage homePage;
    DetailInformationPage detailInformationPage;
    ModalHandle alertHandle;

    @BeforeMethod()
    public void createNewUser() {
        loginPage = new LoginPage();
        manageUserPage = new ManageUserPage();
        createUserPage = new CreateUserPage();
        homePage = new HomePage();
        detailInformationPage = new DetailInformationPage();
        alertHandle = new ModalHandle();

        loginPage.loginWithAdminAccount();
    }

    @Test(dataProvider = "changePasswordAccount", dataProviderClass = DataProviderUser.class)
    public void changePasswordFirstLoginSuccessfully(JsonObject user) {

        homePage.moveToPage("Manage User");
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUser(user);
        manageUserPage.clickDetailFirstUser();
        detailInformationPage.clickClose(detailInformationPage.getUserDetail("Staff Code"));
        String username = detailInformationPage.getUserDetail("Username");
        String accountPassword = username + user.get("password").getAsString();
        homePage.logout();
        loginPage.login(username, accountPassword);
        homePage.changePasswordFirstLogin(user.get("newPassword").getAsString());

        assertThat("verify message success: ",
                alertHandle.getPopupMessageText(),
                equalTo("Change Password success!!!")
        );

        alertHandle.closePopup();
        alertHandle.waitForAlertMessageDisappear("Change Password success!!!");
        homePage.logout();
        loginPage.login(username, user.get("newPassword").getAsString());


        assertThat("verify message login success by new password: ",
                alertHandle.getPopupMessageText(),
                equalTo("Login success!!!")
        );
        alertHandle.closePopup();
    }

    @Test
    public void changePasswordSuccessfully(){

    }

}
