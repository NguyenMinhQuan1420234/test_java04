package com.nashtech.assetmanagement.pages;

import com.nashtech.assetmanagement.utils.Pair;
import org.openqa.selenium.*;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ManageUserPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/
    private final By BTN_CREATE_NEW_USER = By.xpath("//button[text()='Create new user']");
    private final By NOF_LOADING = By.xpath("//div[@id='NotiflixLoadingWrap']/div");
    private final By FIRST_USER = By.xpath("//tbody/tr[1]");
    private final String RANDOM_USER = "//tbody/tr[%s]";
    private final Pair<String, String> LBL_HEADER_TABLE = Pair.of("xpath", "//th[text()='%s']/button");
    private final Pair<String, String> LBL_DATA_LIST = Pair.of("xpath", "//div[@class='table-user-list']//th[text()='%s']/ancestor::thead/following-sibling::tbody//td[%s]");
    private final By LBL_USER_LIST = By.xpath("//div/h3");
    private final By LBL_LOCATION_LIST = By.xpath("(//div[@class='detail-item']/div[text()='Location']/following-sibling::div)[i]");
    private final By BTN_LAST_PAGE = By.xpath("//div[@class='paging']//button[last()-1]");
    private final By BTN_NEXT_PAGE = By.xpath("//button[text()='Next']");
    private final Pair<String, String> BTN_HEADER_SORT = Pair.of("xpath","//th[text()='%s']//button");
    private final Pair<String, String> CHK_FILTER_TYPE = Pair.of("id","type%s");

    /** -------------------- Page Methods ---------------------*/
    public void clickCreateNewUserButton() {
        try {
            waitForStalenessOfElementLocated(findElement(NOF_LOADING));
            clickElement(BTN_CREATE_NEW_USER);
        }
        catch (NoSuchElementException e) {
            clickElement(BTN_CREATE_NEW_USER);
        }
    }

    public void clickDetailFirstUser() {
        clickElement(FIRST_USER);
    }

    public String getPageTitle() {
        return getText(LBL_USER_LIST);
    }
    /** -------------------- Search User In Page Methods ---------------------*/
    public void clickDetailRandomUser() {
        waitForStalenessOfElementLocated(findElement(NOF_LOADING));
        String number = Integer.toString((int)(Math.random()*5 + 1));
        clickElement(By.xpath(String.format(RANDOM_USER, number)), true);
    }
    public void isSearchCriteriaMatchExpected() {
        int totalPage = parseInt(getText(BTN_LAST_PAGE));

    }
    //Header = {"Staff Code", "Full Name", "Joined Date", "Type"} - sortType = {"ascending", "descending"}
    public void clickSortButton(String Header, String sortType) {
        if(sortType.equals("ascending"))
            findElement(getByLocator(BTN_HEADER_SORT, Header)).click();
        else if (sortType.equals("descending")) {
            findElement(getByLocator(BTN_HEADER_SORT, Header)).click();
            findElement(getByLocator(BTN_HEADER_SORT, Header)).click();
        }
    }
    public int getTotalPage() {
        try {
            int totalPage = parseInt(getText(BTN_LAST_PAGE));
            return totalPage;
        }
        catch(TimeoutException e) {
            int totalPage = 1;
            return totalPage;
        }
    }
    public void sortByHeader(String sortHeader, String sortType) {
        clickSortButton(sortHeader, sortType);
        int totalPage = getTotalPage();
        switch (sortHeader) {
            case "Staff Code":
                for (int page = 1; page <= totalPage; page++) {
                    waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, sortHeader, "1"));
                    List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, sortHeader,"1"));
                    for(WebElement data: dataList)
                        System.out.println(data.getText());
                    try {
                        clickElement(BTN_NEXT_PAGE);
                    }
                    catch(ElementClickInterceptedException e) {
                        clickElement(BTN_NEXT_PAGE);
                    }

                }
                break;
            case "Full Name":

                break;
            case "Username":

                break;
            case "Joined Date":

                 break;
            case "Type":

                 break;
        }
//        List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_TABLE_DATA_LIST, sortHeader));


    }
}
