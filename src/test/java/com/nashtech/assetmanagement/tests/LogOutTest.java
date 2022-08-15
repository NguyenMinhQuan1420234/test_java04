package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.pages.HomePage;
import com.nashtech.assetmanagement.pages.LoginPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogOutTest extends BaseTest{

        LoginPage loginPage;
        HomePage homePage;
        ModalHandle modalHandle;
        String username, password;

        private static final Logger LOGGER = LogManager.getLogger(com.nashtech.assetmanagement.tests.LoginTest.class);

        @BeforeMethod
        public void beforeMethod (){
            loginPage = new LoginPage();
            homePage = new HomePage();
            modalHandle = new ModalHandle();

        }
        @Test
        public void logoutSuccessfully(){
            loginPage.loginWithDefaultAccount();
            modalHandle.closeAlert();
            LOGGER.info("logoutSuccessfully");
            loginPage.logout();
            assertThat("Verify error message", modalHandle.getAlertMessageText(), equalTo("Logout successfully!"));
        }
        @Test
        public void logoutUnSuccessfully(){
        loginPage.loginWithDefaultAccount();
        modalHandle.closeAlert();
        LOGGER.info("logoutSuccessfully");
        loginPage.logout();
        loginPage.clickLogoutCancelBtn();
            String actualUsername = homePage.getUserName();
            assertThat("Verify username", actualUsername, equalTo("admin"));
    }
}

