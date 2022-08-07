package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class ManageUserPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/
    private final By BTN_CREATE_NEW_USER = By.xpath("//button[text()='Create new user']");
    private final By NOF_LOADING = By.xpath("//div[@id='NotiflixLoadingWrap']/div");
    private final By FIRST_USER = By.xpath("//tbody/tr[1]");
    private final By LBL_USER_LIST = By.xpath("//div/h3");
    private final By LBL_LOCATION_LIST = By.xpath("(//div[@class='detail-item']/div[text()='Location']/following-sibling::div)[i]");
    private final By BTN_PAGING = By.xpath("//button[@class='btn btn-outline-danger']");


    /** -------------------- Page Methods ---------------------*/
    public void clickCreateNewUserButton() {
        try {
            waitForStalenessOfElementLocated(findElement(NOF_LOADING));
            clickElement(BTN_CREATE_NEW_USER);
        }
        catch (NoSuchElementException e) {
            clickElement(BTN_CREATE_NEW_USER);
        }
    }

    public void clickDetailFirstUser() {
        clickElement(FIRST_USER);
    }

    public String getPageTitle() {
        return getText(LBL_USER_LIST);
    }

}
