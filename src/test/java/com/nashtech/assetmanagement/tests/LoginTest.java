package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.constants.UrlConstants;
import com.nashtech.assetmanagement.pages.BasePage;
import com.nashtech.assetmanagement.pages.HomePage;
import com.nashtech.assetmanagement.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    String username, password;

    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void beforeMethod (){
        loginPage = new LoginPage();
        homePage = new HomePage();
        BasePage.navigate(UrlConstants.LOGIN_PAGE);
        username= System.getProperty("USERNAME");
        password= System.getProperty("PASSWORD");
    }
    @Test
    public void loginSuccessfullyWithValidAccount() {
        LOGGER.info("loginSuccessfullyWithValidAccount");
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        String actualUsername = homePage.getUserName();
        assertThat("Verify username", actualUsername, equalTo("quann"));

    }
    @Test
    public void loginUnsuccessfullyWithEmptyUsername(){
        LOGGER.info("loginUnsuccessfullyWithEmptyUsername");
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        boolean isErrorIconShown = loginPage.isErrorIconDisplayedInUsername();
        assertThat("Verify username", isErrorIconShown, equalTo(Boolean.TRUE));
    }
    @Test
    public void loginUnsuccessfullyWithEmptyPassword(){
        LOGGER.info("loginUnsuccessfullyWithEmptyPassword");
        loginPage.inputUserName(username);
        loginPage.clickLoginBtn();
        boolean isErrorIconDisplayedInPassword = loginPage.isErrorIconDisplayedInPassword();
        assertThat("Verify username", isErrorIconDisplayedInPassword, equalTo(Boolean.TRUE));
    }
    @Test
    public void loginUnsuccessfullyWithInvalidUsername(){
        LOGGER.info("loginUnsuccessfullyWithInvalidUsername");
        loginPage.inputUserName("hoadoan123456");
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getErrorMessage();
        assertThat("Verify error message", errorMsg, equalTo("Invalid username or password!"));
    }
    @Test
    public void loginUnsuccessfullyWithInvalidPassword(){
        LOGGER.info("loginUnsuccessfullyWithInvalidPassword");
        loginPage.inputUserName(username);
        loginPage.inputPassword("Password");
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getErrorMessage();
        assertThat("Verify error message", errorMsg, equalTo("Invalid username or password!"));
    }
}
