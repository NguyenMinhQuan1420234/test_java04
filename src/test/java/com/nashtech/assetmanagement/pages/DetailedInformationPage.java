package com.nashtech.assetmanagement.pages;

import org.openqa.selenium.By;

public class DetailedInformationPage extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    private final By LBL_STAFFCODE = By.xpath("//div[contains(@id,'detailUser')][1]" +
            "//div[text()='Staff Code']/following-sibling::div");

    /** -------------------- Page Methods ---------------------*/
    public String getUserDetail(String label) {
        By LBL_USERDETAIL = By.xpath(String.format("//div[contains(@id,'detailUser')][1]" +
                "//div[text()='%s']/following-sibling::div", label));
        return getText(LBL_USERDETAIL);
    }

    public void clickClose(String staffCode) {
        clickElement(By.xpath(String.format("//div[@id='detailUserViewModal%s']//button", staffCode)));
    }
}