package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import org.openqa.selenium.By;

public class DetailedInformationPage extends BasePage {
    /** ------------------ Web Elements ----------------------*/
    private final By LBL_STAFFCODE = By.xpath("//div[contains(@id,'detailUser')][1]" +
            "//div[text()='Staff Code']/following-sibling::div");
    private final String MODAL_DETAIL_USER_INFORMATION_LABEL = "(//div[@style='display: block;']//div[@class='label'])[%s]";
    private final By BUTTON_CANCEL = By.xpath("//div[@style='display: block;']//button");

    /** -------------------- Page Methods ---------------------*/
    public String getUserDetail(String label) {
        By LBL_USERDETAIL = By.xpath(String.format("//div[contains(@id,'detailUser')][1]" +
                "//div[text()='%s']/following-sibling::div", label));
        return getText(LBL_USERDETAIL);
    }

    public void clickClose() {
        clickElement(BUTTON_CANCEL);
    }

    public String getDetailInformationLabel(String index) {
        return getText(By.xpath(String.format(MODAL_DETAIL_USER_INFORMATION_LABEL, index)));
    }
}
