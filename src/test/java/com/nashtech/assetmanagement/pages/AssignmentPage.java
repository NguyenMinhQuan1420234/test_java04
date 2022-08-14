package com.nashtech.assetmanagement.pages;
import static com.nashtech.assetmanagement.pages.shared.TableDataPage.*;
import org.openqa.selenium.By;


public class AssignmentPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/

    private final By INP_SearchFirstName = By.xpath("//input[@type='text']");
    private final By BTN_Search = By.cssSelector("div.search > div:nth-child(2) > button");

    private final By BTN_Delete = By.cssSelector("div.search > button");
    private final By BTN_Disable = By.id("#disable-button");

    private final By BTN_Cancel = By.id("#cancel-button");

    private final By BTN_Edit = By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(6) > button.btn.btn-outline-secondary.border-0");
    private final By TBL_USER = By.cssSelector("#root > div:nth-child(3)");
    private final By TBL_ASSET = By.cssSelector("div:nth-child(3) > div.user-list > div.table-user-list");
    private final By LBL_FirstResult = By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(2)");
    private final By LBL_SuccessDeleteMessage = By.xpath("//div[3]/div/div/div[1]/div[2]");
    private final By LBL_ErrorSearchMessage = By.xpath("//div[3]/div/div/div[1]/div[2]");

    /** -------------------- Page Methods ---------------------*/
    public void inputSearchUserName(String search) {
        inputText(INP_SearchFirstName, search);
    }

    public void clickSearchBtn() {
        clickElement(BTN_Search);
    }

    public void clickEditBtn() {
        clickElement(BTN_Edit);
    }

    public void clickDeleteBtn() {
        clickElement(BTN_Delete);
    }


    public void clickDisableBtn() {
        clickElement(BTN_Disable);
    }

    public void clickCancelBtn() {
        clickElement(BTN_Cancel);
    }

    public String getTableUser() {return getText(TBL_USER);}

    public String getTableAsset() {
        return getText(TBL_ASSET);
    }

    public String getSuccessDeleteMessage() {
        return getText(LBL_SuccessDeleteMessage);
    }

    public String getErrorSearchMessage() {
        return getText(LBL_ErrorSearchMessage);
    }

    public String getFirstResult() {
        return  getText(LBL_FirstResult);
    }

}
