package com.nashtech.assetmanagement.pages;

import com.nashtech.assetmanagement.utils.Pair;
import static com.nashtech.assetmanagement.utils.RandomStringUtil.*;

import com.nashtech.assetmanagement.utils.RandomStringUtil;
import org.openqa.selenium.By;

public class CreateAssetPage extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    private final By TXT_NAME = By.id("name");
    private final By BTN_DDL_CATEGORY = By.id("dropdownMenuLink");
    // options : Laptop ; Monitor ; Personal Computer ; Phone
    private final Pair<String, String> BTN_DDL_ITEMS = Pair.of("xpath", "//span[@class='dropdown-item'][text()='%s']");
    private final By TXT_SPECIFICATION = By.id("specification");
    private final By TXT_INSTALLED_DATE = By.id("installedDate");
    private final Pair<String, String> CHK_STATE_OPTIONS = Pair.of("xpath", "//label[text()='%s']/preceding-sibling::input");
    // Save ; Cancel
    private final Pair<String, String> BTN_SAVE_CANCEL = Pair.of("xpath", "(//button[text()='%s'])[2]");
    private final By LBL_ADD_NEW_CATEGORY = By.id("add-new-category");
    // category name: 1 ; prefix: 2
    private final Pair<String, String> TXT_ADD_NEW_CATEGORY = Pair.of("xpath", "//label[@for='category']/following-sibling::div//input[@type='text'][%s]");
    private final By BTN_CHECK_ICON = By.cssSelector("svg[data-testid='CheckIcon']");
    private final By BTN_CLOSE_ICON = By.cssSelector("svg[data-testid='CloseIcon']");

    // SUCCESSFULLY ADDED!!
    /** -------------------- Page Methods ---------------------*/
    public void clickButtonNewAssetPage(String text) {
        waitForElementToBeClickable(getByLocator(BTN_SAVE_CANCEL, text)).click();
    }
    public void inputName(String name) {
        inputText(TXT_NAME, name);
    }
    public void selectCategory(String category) {
        clickElement(BTN_DDL_CATEGORY);
        clickElement(getByLocator(BTN_DDL_ITEMS, category));
        clickElement(BTN_DDL_CATEGORY);
    }
    public void inputSpecification(String text) {
        inputText(TXT_SPECIFICATION, text);
    }
    public void inputInstalledDate(String text) {
        inputText(TXT_INSTALLED_DATE, text);
    }
    public void selectState(String text) {
        clickElement(getByLocator(CHK_STATE_OPTIONS, text));
    }
    public boolean isSaveButtonNotEnable() {
        return !(waitForVisibilityOfElementLocated(getByLocator(BTN_SAVE_CANCEL, "Save")).isEnabled());
    }
    public void clickAddNewCateGory() {
        clickElement(BTN_DDL_CATEGORY);
        clickElement(LBL_ADD_NEW_CATEGORY);
    }
    public void inputNewCategoryName(String text) {
        inputText(getByLocator(TXT_ADD_NEW_CATEGORY,"1"), text + RandomStringUtil.randomName());
    }
    public void inputNewCategoryPrefix(String text) {
        inputText(getByLocator(TXT_ADD_NEW_CATEGORY,"2"), text + RandomStringUtil.randomName());
    }
    public void clickAddIcon() {
        clickElement(BTN_CHECK_ICON);
    }
}
