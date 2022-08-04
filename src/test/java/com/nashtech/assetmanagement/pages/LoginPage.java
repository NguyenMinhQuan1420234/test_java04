package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/
    private final By TXT_USERNAME = By.id("username");
    private final By TXT_PASSWORD = By.id("password");
    private final By BTN_LOGIN = By.cssSelector("button");
    private final By LBL_ERROR_MSG = By.id("name");
    private final By ICON_USERNAME_ERROR = By.cssSelector("#userName-wrapper .is-invalid");
    private final By ICON_PASSWORD_ERROR = By.cssSelector("#password-wrapper .is-invalid");


    /** -------------------- Page Methods ---------------------*/
    public void inputUserName(String username) {
        inputText(TXT_USERNAME, username);
    }

    public void inputPassword(String password) {
        inputText(TXT_PASSWORD, password);
    }

    public void clickLoginBtn() {
        clickElement(BTN_LOGIN);
    }

    public void loginWithAdminAccount() {
        inputUserName(System.getProperty("USERNAME"));
        inputPassword(System.getProperty("PASSWORD"));
        clickLoginBtn();
    }

    public void login(String username, String password) {
        inputUserName(username);
        inputPassword(password);
        clickLoginBtn();
    }

    public String getErrorMessage() {
        return getText(LBL_ERROR_MSG);
    }

    public boolean isErrorIconDisplayedInUsername() {
        return isElementDisplayed(ICON_USERNAME_ERROR);
    }

    public boolean isErrorIconDisplayedInPassword() {
        return isElementDisplayed(ICON_PASSWORD_ERROR);
    }
}
