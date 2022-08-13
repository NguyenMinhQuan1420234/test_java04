package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.constants.UrlConstants;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchTest extends BaseTest {
    LoginPage loginPage;
    CreateUserPage createUserPage;
    HomePage homePage;
    ManageUserPage manageUserPage;
    DetailedInformationPage detailInformationPage;

    ModalHandle alertHandle;
    AssignmentPage searchPage;

    private static final Logger LOGGER = LogManager.getLogger(com.nashtech.assetmanagement.tests.LoginTest.class);

    @BeforeMethod
    public void beforeMethod() {
        searchPage = new AssignmentPage();
        loginPage = new LoginPage();
        homePage = new HomePage();
        alertHandle = new ModalHandle();
        createUserPage =new CreateUserPage();
    }
    @Test
    public void SearchForDisabledUserSuccessfully() {
        LOGGER.info("SearchForDisabledUserSuccessfully");
        loginPage.loginWithDefaultAccount();
        BasePage.navigate(UrlConstants.MANAGE_USER_PAGE);
//        createUserPage.createUser();
        searchPage.inputSearchUserName("tung");
        searchPage.clickSearchBtn();
        searchPage.getFirstResult();
        String SearchRsult = searchPage.getFirstResult();
        assertThat("Verify error message", SearchRsult, equalTo("tung tung"));
        searchPage.clickDeleteBtn();
        searchPage.clickDisableBtn();
        searchPage.getFirstResult();
        String SuccessMsg = searchPage.getSuccessDeleteMessage();
        assertThat("Verify error message", SuccessMsg, equalTo("User is disabled"));
    }

    @Test
    public void SearchForDisabledUserUnsuccessfully() {
        LOGGER.info("SearchForDisabledUserSuccessfully");
        loginPage.loginWithDefaultAccount();
        BasePage.navigate(UrlConstants.MANAGE_USER_PAGE);
        searchPage.inputSearchUserName("tung");
        searchPage.clickSearchBtn();
        searchPage.clickCancelBtn();
        searchPage.getFirstResult();
        String SearchRsult = searchPage.getFirstResult();
        assertThat("Verify error message", SearchRsult, equalTo("tung tung"));
    }
    @Test
    public void SearchForDisabledAssetSuccessfully() {
        LOGGER.info("SearchForDisabledUserSuccessfully");
        loginPage.loginWithDefaultAccount();
        BasePage.navigate(UrlConstants.MANAGE_ASSETS_PAGE);
//        createUserPage.createUser();
        searchPage.inputSearchUserName("tung");
        searchPage.clickSearchBtn();
        searchPage.getFirstResult();
        String SearchRsult = searchPage.getFirstResult();
        assertThat("Verify error message", SearchRsult, equalTo("tung tung"));
        searchPage.clickDeleteBtn();
        searchPage.clickDisableBtn();
        searchPage.getFirstResult();
        String SuccessMsg = searchPage.getSuccessDeleteMessage();
        assertThat("Verify error message", SuccessMsg, equalTo("User is disabled"));
    }

    @Test
    public void SearchForDisabledAssetUnsuccessfully() {
        LOGGER.info("SearchForDisabledUserSuccessfully");
        loginPage.loginWithDefaultAccount();
//        BasePage.navigate(UrlConstants.MANAGE_ASSETS_PAGE);
        searchPage.inputSearchUserName("tung");
        searchPage.clickSearchBtn();
        searchPage.clickCancelBtn();
        searchPage.getFirstResult();
        String SearchRsult = searchPage.getFirstResult();
        assertThat("Verify error message", SearchRsult, equalTo("tung tung"));
    }
    @Test
    public void SearchForDisabledAssignmentSuccessfully() {
        LOGGER.info("SearchForDisabledUserSuccessfully");
        loginPage.loginWithDefaultAccount();
        BasePage.navigate(UrlConstants.MANAGE_ASSIGNMENT_PAGE);
//        createUserPage.createUser();
        searchPage.inputSearchUserName("tung");
        searchPage.clickSearchBtn();
        searchPage.getFirstResult();
        String SearchRsult = searchPage.getFirstResult();
        assertThat("Verify error message", SearchRsult, equalTo("tung tung"));
        searchPage.clickDeleteBtn();
        searchPage.clickDisableBtn();
        searchPage.getFirstResult();
        String SuccessMsg = searchPage.getSuccessDeleteMessage();
        assertThat("Verify error message", SuccessMsg, equalTo("User is disabled"));
    }

    @Test
    public void SearchForDisabledAssignmentUnsuccessfully() {
        LOGGER.info("SearchForDisabledUserSuccessfully");
        loginPage.loginWithDefaultAccount();
//        BasePage.navigate(UrlConstants.MANAGE_ASSIGNMENT_PAGE);
        searchPage.inputSearchUserName("tung");
        searchPage.clickSearchBtn();
        searchPage.clickCancelBtn();
        searchPage.getFirstResult();
        String SearchRsult = searchPage.getFirstResult();
        assertThat("Verify error message", SearchRsult, equalTo("tung tung"));
    }

}
