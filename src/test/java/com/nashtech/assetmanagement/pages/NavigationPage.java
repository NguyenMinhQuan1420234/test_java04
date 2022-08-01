package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class NavigationPage extends BasePage{

    /** ------------------ Web Elements ----------------------*/

    /**-------Head Bar-------*/

    By LBL_USERNAME = By.id("user_header");
    By DDL_HEADER_MENU = By.id("dropdownMenuReference");
    By OPT_LOGOUT = By.xpath("//li[text()='Logout']");
    By OPT_CHANGEPASSWORD = By.xpath("//a[text()='Change password']");
    By MODAL_LOGOUT = By.id("ModalLabel");
    By BTN_LOGOUT = By.id("btnLogout");
    By BTN_CANCEL = By.id("btnCancel");

    /**-------Side Bar-------*/

    By LNK_MENU_HOME = By.xpath("//a[contains(text(),'Home')]");
    By LNK_MENU_MANAGE_USER = By.xpath("//a[contains(text(),'Manage User')]");
    By LNK_MENU_MANAGE_ASSET = By.xpath("//a[contains(text(),'Manage Asset')]");
    By LNK_MENU_MANAGE_ASSIGNMENT = By.xpath("//a[contains(text(),'Manage Assignment')]");
    By LNK_MENU_REQUEST_FOR_RETURNING = By.xpath("//a[contains(text(),'Request for Returning')]");
    By LNK_MENU_REPORT = By.xpath("//a[contains(text(),'Report')]");

    /** -------------------- Page Methods ---------------------*/

    public String getUserName() {
        return getText(LBL_USERNAME);
    }

    public void moveToPage(String text) {
        By LNK_MENU_PAGE = By.xpath(String.format("//a[contains(text(),'%s')]",text));
        clickElement(LNK_MENU_PAGE);
    }

}
