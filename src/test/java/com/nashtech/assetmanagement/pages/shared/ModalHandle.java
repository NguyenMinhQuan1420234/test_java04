package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ModalHandle extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    public final By MSG_ALERT = By.cssSelector("div[class*='toast-body']>div:last-child");
    public final By BTN_CLOSE = By.cssSelector("div[class*='toast-body']~button");
    public final By ALERT = By.xpath("//div[@role='alert']");
    /** -------------------- Page Methods ---------------------*/
    public String getPopupMessageText() {
        return getText(MSG_ALERT);
    }
    public void closeAlert() {
        List<WebElement> listAlert =  waitForVisibilityOfAllElementsLocatedBy(ALERT);
        for(WebElement popup: listAlert) {
            popup.click();
        }
    }
    public void waitForAlertMessageDisappear() {
        waitForStalenessOfElementLocated(waitForVisibilityOfElementLocated(MSG_ALERT));
    }
}

