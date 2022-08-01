package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class ManageUserPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/
    private final By BTN_CREATE_NEW_USER = By.xpath("//button[text()='Create new user']");
    private final By NOF_LOADING = By.xpath("//div[@id='NotiflixLoadingWrap']/div");
    private final By FIRST_USER = By.xpath("//tbody/tr[1]");
    private final By MSG_CREATE_USER_SUCCESS = By.xpath("//div[@role='alert']/div[2]");
    /** -------------------- Page Methods ---------------------*/
    public void clickCreateNewUserButton() {
        waitForStalenessOfElementLocated(findElement(NOF_LOADING));
        clickElement(BTN_CREATE_NEW_USER);
    }

    public void clickDetailFirstUser() {
        clickElement(FIRST_USER);
    }

    public String getTextCreateMessageSuccessfully() {
        return getText(MSG_CREATE_USER_SUCCESS);
    }
}
