package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.pages.BasePage;
import com.nashtech.assetmanagement.pages.LoginPage;
import com.nashtech.assetmanagement.pages.NavigationPage;
import com.nashtech.assetmanagement.constants.UrlConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends BaseTest{
    LoginPage loginPage;
    NavigationPage navigationPage;
    String username, password;
}
