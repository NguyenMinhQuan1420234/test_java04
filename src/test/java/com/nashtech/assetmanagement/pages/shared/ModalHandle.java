package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import com.nashtech.assetmanagement.utils.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.nashtech.assetmanagement.pages.HomePage.NOF_LOADING;

public class ModalHandle extends BasePage {
    /** -------------------- Toastify Popup Elements---------------------*/
    public final By MSG_ALERT = By.cssSelector("div[class*='toast-body']>div:last-child");
    public final By BTN_CLOSE = By.cssSelector("div[class*='toast-body']~button");
    public final By ALERT = By.xpath("//div[@role='alert']");
    /** -------------------- Modal Popup Elements ---------------------*/
    public final By MODAL_QUESTION = By.xpath("(//div[@class='modal-body confirm-disable']//div)[1]");
    public final By MODAL_NOT_DISABLE = By.xpath("//div[@class='modal fade  show d-block']//div[@class='modal-subtitle']");
    public final By MODAL_STATUS = By.xpath("//div[@class='modal fade  d-none']");
    public final Pair<String, String> BTN_MODAL_ID = Pair.of("xpath", "//div[@class='modal fade  show d-block']//button[@id='%s']");
    public final Pair<String, String> BTN_MODAL_TEXT = Pair.of("xpath","//div[@class='modal fade show d-block']//button[text()='%s']");
    // Disable = 'disable-button' ; Cancel = 'cancel-button' ; Close = 'btnClose'ncel
    /** -------------------- Toastify Popup ---------------------*/

    public String getAlertMessageText() {
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
    public void waitLoadingScreen() {
        waitForVisibilityOfElementLocated(NOF_LOADING);
        waitForStalenessOfElementLocated(findElement(NOF_LOADING));
    }

    /** -------------------- Modal Popup ---------------------*/
    public void clickModalButtonById(String id) {
        waitForVisibilityOfElementLocated(getByLocator(BTN_MODAL_ID, id)).click();
    }
    public void clickModalButtonByText(String text) {
        waitForVisibilityOfElementLocated(getByLocator(BTN_MODAL_TEXT, text)).click();
    }
    public String getModalText() {
        return getText(MODAL_NOT_DISABLE);
    }
    public boolean verifyModalDisappear() {
        if(getAttribute(MODAL_STATUS, "class").equals("modal fade  d-none"))
            return true;
        else
            return false;
    }

}

