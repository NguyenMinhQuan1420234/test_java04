package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class AssetPage extends BasePage {

        /** ------------------ Web Elements ----------------------*/

        private final By INP_SearchFirstName = By.xpath("//input[@type='text']");
        private final By BTN_Search = By.cssSelector("div.search > div:nth-child(2) > button");
        private final By BTN_Delete = By.cssSelector("div.search > button");
        private final By BTN_Edit = By.cssSelector("div.search > button");
        private final By BTN_Disable = By.id("#disable-button");

        private final By BTN_Cancel = By.id("#cancel-button");

        private final By DDL_STATE = By.cssSelector("div:nth-child(3) > div.user-list > div.table-board > div.left-board > div > div:nth-child(1)");

        private final By CHK_STATE_ALL = By.xpath("//label[@for='typeAll']");
        private final By CHK_STATE_ASSIGNED = By.xpath("//label[@for='typeAssigned']");
        private final By CHK_STATE_AVAILABLE = By.xpath("//label[@for='typeAvailable']");
        private final By CHK_STATE_NOT_AVAILABLE = By.xpath("//label[@for='typeNotAvailable']");
        private final By CHK_STATE_WAITING_FOR_RECYCLING = By.xpath("//label[@for='typeWaiting']");
        private final By CHK_STATE_RECYCLED = By.xpath("//label[@for='typeRecycled']");

        private final By DDL_CATEGORY = By.cssSelector("div:nth-child(3) > div.user-list > div.table-board > div.left-board > div > div:nth-child(2)");
        private final By CHK_CATEGORY_ALL = By.xpath("//label[@for='typeAll']");
        private final By CHK_CATEGORY_LAPTOP = By.xpath("//label[@for='LP']");
        private final By CHK_CATEGORY_MONITOR = By.xpath("//label[@for='MT']");
        private final By CHK_CATEGORY_PERSONAL_COMPUTER = By.xpath("//label[@for='PC']");
        private final By CHK_CATEGORY_BLUETOOTH_MOUSE = By.xpath("//label[@for='BM']");

        private final By TBL_ASSET = By.cssSelector("div:nth-child(3) > div.user-list > div.table-user-list");
        private final By LBL_FirstResult = By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(2)");
        private final By LBL_SuccessDeleteMessage = By.xpath("//div[3]/div/div/div[1]/div[2]");
        private final By LBL_ErrorSearchMessage = By.xpath("//div[3]/div/div/div[1]/div[2]");

        private final By TXT_ASSET_NAME = By.id("name");
        private final By TXT_SPECIFICATION = By.id("specification");
        private final By DDL_ASSET_CATEGORY1 = By.id("dropdownMenuLink");
        private final By DDL_ASSET_CATEGORY2 = By.id("add-new-category");
        private final By DDL_ASSET_CATEGORY3 = By.xpath("//div[@class='input-group px-3']//input[1]");
        private final By DDL_ASSET_CATEGORY4 = By.xpath("//div[@class='input-group px-3']//input[2]");
        private final By DDL_ASSET_CATEGORY_ITEM22 = By.cssSelector("li:nth-child(22) > span");
        private final By DDL_ASSET_CATEGORY_ACCEPT = By.cssSelector("li:nth-child(34) > div > div > svg:nth-child(1)");
        private final By DDL_ASSET_CATEGORY_CANCEL = By.cssSelector("li:nth-child(34) > div > div > svg:nth-child(2)");
        private final By TXT_INSTALLED_DATE = By.id("dropdownMenuLink");
        private final By RDO_STATE_AVAILABLE = By.id("available");
        private final By RDO_STATE_NOT_AVAILABLE = By.id("notAvailable");
        private final By BTN_SAVE = By.id("save");
        private final By BTN_CANCEL = By.id("cancel");

//        ERROR: Category and Prefix are already existed


        /** -------------------- Page Methods ---------------------*/

        public void inputAssetName(String assetName) {
                inputText(TXT_ASSET_NAME, assetName);
        }

        public void inputSpecification(String specification) {inputText(TXT_SPECIFICATION, specification);}

        public void clickCategory(String Category) {
                inputText(DDL_ASSET_CATEGORY1, Category);
        }
        public void selectCategory(String Category) {
                inputText(DDL_ASSET_CATEGORY2, Category);
        }
        public void inputInstallDated(String installDated) {
                inputText(TXT_INSTALLED_DATE, installDated);
        }

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

        public void clickCheckoutStateDropDownList() {clickElement(CHK_STATE_ALL);}
        public void clickCheckoutAllState() {clickElement(CHK_STATE_ALL);}
        public void clickCheckoutAssignedState() {clickElement(CHK_STATE_ASSIGNED);}
        public void clickCheckoutAvailableState() {clickElement(CHK_STATE_AVAILABLE);}
        public void clickCheckoutNotAvailableState() {clickElement(CHK_STATE_NOT_AVAILABLE);}
        public void clickCheckoutWaitingState() {clickElement(CHK_STATE_WAITING_FOR_RECYCLING);}
        public void clickCheckoutRecycledState() {clickElement(CHK_STATE_RECYCLED);}

        public void clickCheckoutCategoryDropDownList() {clickElement(DDL_CATEGORY);}
        public void clickCheckoutAllCategory() {clickElement(CHK_CATEGORY_ALL);}
        public void clickCheckoutLatopCategory() {clickElement(CHK_CATEGORY_LAPTOP);}
        public void clickCheckoutMonitorCategorye() {clickElement(CHK_CATEGORY_MONITOR);}
        public void clickCheckoutPersonalCategory() {clickElement(CHK_CATEGORY_PERSONAL_COMPUTER);}
        public void clickCheckoutMouseCategory() {clickElement(CHK_CATEGORY_BLUETOOTH_MOUSE);}


}
