package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import org.openqa.selenium.By;

public class ModalHandle extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    public final By MSG_POPUP = By.cssSelector("div[class*='toast-body']>div:last-child");
    public final By BTN_CLOSE = By.cssSelector("div[class*='toast-body']~button");
    /** -------------------- Page Methods ---------------------*/
    public String getPopupMessageText() {
        return getText(MSG_POPUP);
    }
    public void closePopup() {
        clickElement(BTN_CLOSE);
    }
    public void waitForAlertMessageDisappear() {
        waitForStalenessOfElementLocated(findElement(MSG_POPUP));
    }

}

