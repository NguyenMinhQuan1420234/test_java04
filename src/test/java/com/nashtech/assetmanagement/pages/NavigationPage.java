package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class NavigationPage extends BasePage{

    /** ------------------ Web Elements ----------------------*/
    By lblUserName = By.id("userName-value");
    By logOutBtn = By.id("submit");

    /** -------------------- Page Methods ---------------------*/

    public String getUserName() {
        return getText(lblUserName);
    }
}
