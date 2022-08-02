package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import org.openqa.selenium.By;

public class AlertHandle extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    public final String loginMsg = "Login success!!!";
    public final String createMsg = "Successfully added!!";
    public final String disableMsg = "User is disabled";
    /** -------------------- Page Methods ---------------------*/
    public String getPopupMessageText(String message) {
        return getText(By.xpath(String.format("//div[text()='%s']", message)));
    }

}

