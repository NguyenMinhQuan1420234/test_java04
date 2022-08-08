package com.nashtech.assetmanagement.pages;

import com.nashtech.assetmanagement.utils.Pair;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;

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
    public void clickSortButton(String Header, String sortType) throws InterruptedException {
        if(sortType.equals("descending"))
            waitForElementToBeClickable(getByLocator(BTN_HEADER_SORT, Header)).click();
        else if (sortType.equals("ascending")) {
            waitForElementToBeClickable(getByLocator(BTN_HEADER_SORT, Header)).click();
            waitForElementToBeClickable(getByLocator(BTN_HEADER_SORT, Header)).click();
        }
        sleep(2000);
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
    public boolean verifySortOrder(ArrayList<String> list) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i+1).compareTo(list.get(i)) < 0)
                return false;
            }
        return true;
    }
    public boolean verifySortByHeader(String sortHeader, String sortType) throws InterruptedException {
        clickSortButton("Staff Code", "ascending");
        int totalPage = getTotalPage();
        ArrayList<String> listOfSortValue = new ArrayList<>();
        boolean flag = false;
        switch (sortHeader) {
            case "Staff Code":
                for (int page = 1; page <= totalPage; page++) {
                    isElementDisplayed(getByLocator(LBL_DATA_LIST, sortHeader, "1"));
                    waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, sortHeader, "1"));
                    List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, sortHeader,"1"));
                    listOfSortValue.clear();
                    for(WebElement data: dataList) {
                        System.out.println(data.getText());
                        listOfSortValue.add(data.getText());
                    }
                    flag = verifySortOrder(listOfSortValue);
                    System.out.println(flag);
                    try {
                        clickElement(BTN_NEXT_PAGE);
                    }
                    catch(ElementClickInterceptedException e) {
                        clickElement(BTN_NEXT_PAGE);
                    }
                }
                System.out.println(listOfSortValue);
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
        return flag;
    }

}
