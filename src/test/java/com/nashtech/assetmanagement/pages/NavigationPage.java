package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class NavigationPage extends BasePage{

    /** ------------------ Web Elements ----------------------*/

    /**-------Head Bar-------*/

    public static final By LBL_USERNAME = By.id("user_header");
    public static final By DDL_HEADER_MENU = By.id("dropdownMenuReference");
    public static final By OPT_LOGOUT = By.xpath("//li[text()='Logout']");
    public static final By OPT_CHANGEPASSWORD = By.xpath("//a[text()='Change password']");
    /**-------Logout-------*/
    public static final By MODAL_LOGOUT = By.xpath("//div[@id='logoutModal']//h5");
    public static final By BTN_LOGOUT = By.id("btnLogout");
    public static final By BTN_CANCEL_LOGOUT = By.xpath("//div[@id='logoutModal']//button[@id='btnCancel']");
    /**-------Change Password-------*/
    public static final By MODAL_CHANGEPASSWORD = By.xpath("//div[@id='changePasswordModal']//h5");
    public static final By TXT_OLDPASS = By.id("old-pass");
    public static final By TXT_NEWPASS = By.id("new-pass");
    public static final By TXT_FIRSTTIME_CHANGEPASSWORD = By.id("pass");
    public static final By BTN_SAVE_FIRSTLOGIN = By.cssSelector(".change-password~.button-save>button");
    public static final By BTN_SAVE = By.xpath("//div[@id='changePasswordModal']//button[text()='Save']");
    public static final By BTN_CANCEL_CHANGEPASSWORD = By.xpath("//div[@id='changePasswordModal']//button[@id='btnCancel']");
    public static final By FRM_CHANGEPASSWORD_SUCCESSFULLY = By.xpath("//p");
    public static final By BTN_CLOSE_FORM = By.xpath("//button[text()='Close']");
    /**-------Side Bar-------*/

    public static final By LNK_MENU_HOME = By.xpath("//a[contains(text(),'Home')]");
    public static final By LNK_MENU_MANAGE_USER = By.xpath("//a[contains(text(),'Manage User')]");
    public static final By LNK_MENU_MANAGE_ASSET = By.xpath("//a[contains(text(),'Manage Asset')]");
    public static final By LNK_MENU_MANAGE_ASSIGNMENT = By.xpath("//a[contains(text(),'Manage Assignment')]");
    public static final By LNK_MENU_REQUEST_FOR_RETURNING = By.xpath("//a[contains(text(),'Request for Returning')]");
    public static final By LNK_MENU_REPORT = By.xpath("//a[contains(text(),'Report')]");

    /** -------------------- Page Methods ---------------------*/

    public String getUserName() {
        return getText(LBL_USERNAME);
    }

    public void moveToPage(String text) {
        By LNK_MENU_PAGE = By.xpath(String.format("//a[contains(text(),'%s')]",text));
        clickElement(LNK_MENU_PAGE);
    }

    public void logout() {
        clickElement(DDL_HEADER_MENU);
        clickElement(OPT_LOGOUT);
        clickElement(BTN_LOGOUT);
    }

    public void changePassword(String oldPass, String newPass) {
        clickElement(DDL_HEADER_MENU);
        clickElement(OPT_CHANGEPASSWORD);
        clearAndType(TXT_OLDPASS, oldPass, true);
        clearAndType(TXT_NEWPASS, newPass, true);
        clickElement(BTN_SAVE);
    }

    public void changePasswordFirstLogin(String newPass) {
        inputText(TXT_FIRSTTIME_CHANGEPASSWORD, newPass);
        clickElement(BTN_SAVE_FIRSTLOGIN);
    }


}
