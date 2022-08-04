package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import org.openqa.selenium.By;

public class ModalHandle extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    public final By MSG_POPUP = By.cssSelector("div[class*='toast-body']>div:last-child");
    /** -------------------- Page Methods ---------------------*/
    public String getPopupMessageText() {
        return getText(MSG_POPUP);
    }
    public void closePopup() {
        clickElement(MSG_POPUP);
    }
    public void waitForAlertMessageDisappear(String message) {
        waitForInvisibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']", message)));
    }

}

