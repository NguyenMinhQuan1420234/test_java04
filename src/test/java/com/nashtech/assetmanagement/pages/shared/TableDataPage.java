package com.nashtech.assetmanagement.pages.shared;

import com.nashtech.assetmanagement.pages.BasePage;
import com.nashtech.assetmanagement.utils.Pair;
import org.openqa.selenium.By;

public class TableDataPage extends BasePage {
    /** ------------------ Web Elements ----------------------*/

    public static final By FIRST_USER_NAME = By.xpath("//tbody/tr[1]/td[2]");
    public static final String RANDOM_USER = "//tbody/tr[%s]";
    public static final Pair<String, String> LBL_HEADER_TABLE = Pair.of("xpath", "//th[text()='%s']/button");
    public static final Pair<String, String> LBL_DATA_LIST = Pair.of("xpath", "//div[@class='table-user-list']//th[text()='%s']/ancestor::thead/following-sibling::tbody//td[%s]");
    public static final By LBL_USER_LIST = By.xpath("//div/h3");
    public static final By LBL_LOCATION_LIST = By.xpath("(//div[@class='detail-item']/div[text()='Location']/following-sibling::div)[i]");
    public static final By BTN_LAST_PAGE = By.xpath("//div[@class='paging']//button[last()-1]");
    public static final By BTN_NEXT_PAGE = By.xpath("//button[text()='Next']");
    public static final Pair<String, String> BTN_HEADER_SORT = Pair.of("xpath","//th[text()='%s']//button");
    public static final Pair<String, String> CHK_FILTER_TYPE = Pair.of("id","type%s");
    public static final By DDL_FILTER = By.id("dropMenuFilterType");
    public static final By TXT_SEARCH_BAR = By.cssSelector("div.search input");
    public static final By BTN_SEARCH = By.id("btnSearch");
    public static final Pair<String, String> BTN_EDIT_USER = Pair.of("xpath", "//tr/td[text()='%s']/following-sibling::td/button[@id='btnEdit']");
    public static final Pair<String, String> BTN_DISABLE_USER = Pair.of("xpath", "//tr/td[text()='%s']/following-sibling::td/button[@id='btnHighlight']");
    public static final Pair<String, String> BTN_EDIT_DISABLE = Pair.of("xpath", "//tr/td[text()='%s']/following-sibling::td/button[@id='%s']");
    /** -------------------- Page Methods ---------------------*/

}
