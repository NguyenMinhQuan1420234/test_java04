package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/
    private final By txtUserName = By.id("userName");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.id("login");
    private final By lblErrorMessage = By.id("name");
    private final By iconUsernameError= By.cssSelector("#userName-wrapper .is-invalid");
    private final By iconPasswordError= By.cssSelector("#password-wrapper .is-invalid");

    /** -------------------- Page Methods ---------------------*/
    public void inputUserName(String username) {
        inputText(txtUserName, username);
    }

    public void inputPassword(String password) {
        inputText(txtPassword, password);
    }

    public void clickLoginBtn() {
        clickElement(btnLogin);
    }

    public String getErrorMessage() {
        return getText(lblErrorMessage);
    }

    public boolean isErrorIconDisplayedInUsername() {
        return isElementDisplayed(iconUsernameError);
    }

    public boolean isErrorIconDisplayedInPassword() {
        return isElementDisplayed(iconPasswordError);
    }
}
