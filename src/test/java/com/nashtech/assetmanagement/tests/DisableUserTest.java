package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderSearch;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

//        loginPage.loginWithDefaultAccount();
//        alertHandle.closeAlert();
    }
    /** ---- Disable = 'disable-button' ; Cancel = 'cancel-button' ; Close = 'btnClose'----*/
    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void adminDisableUserSuccessfully(JsonObject user) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        // bf
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUserDefault(user);
        alertHandle.closeAlert();
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("disable-button");
        assertThat(
                "verify delete user successful: ",
                alertHandle.getAlertMessageText(),
                equalTo("User is disabled")
        );
    }
    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyAdminClickCancelDisableUserModal(JsonObject user) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        // bf
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUserDefault(user);
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("cancel-button");

        assertThat(
                "verify cancel button works: ",
                alertHandle.verifyModalDisappear(),
                equalTo(true)
        );
    }
    @Test(dataProvider = "searchDataForDisableTest", dataProviderClass = DataProviderSearch.class)
    public void verifyAdminCamnotDisableAssignedUser(String user) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        //
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.inputSearchCriteria(user);
        manageUserPage.clickSearchButton();
        manageUserPage.waitForUserAppear(user);
        manageUserPage.clickFirstUserButton("disable");

        assertThat(
                "verify delete user successful: ",
                alertHandle.getModalText(),
                equalTo("There are valid assignments belonging to this user.\n" +
                        "Please close all assignments before disabling user.")
        );
    }

    @Test(dataProvider = "searchDataForDisableTest", dataProviderClass = DataProviderSearch.class)
    public void verifyAdminClickCloseDisableAssignedUserModal(String user) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        // bf
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.inputSearchCriteria(user);
        manageUserPage.clickSearchButton();
        manageUserPage.waitForUserAppear(user);
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("btnClose");

        assertThat(
                "verify modal is closed: ",
                alertHandle.verifyModalDisappear(),
                equalTo(true)
        );
    }
}
