package com.nashtech.assetmanagement.pages;

import com.nashtech.assetmanagement.utils.Pair;
import org.openqa.selenium.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

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
    private final By DDL_FILTER = By.id("dropMenuFilterType");
    private final By TXT_SEARCH_BAR = By.cssSelector("div.search input");
    private final By BTN_SEARCH = By.id("btnSearch");
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
        WebElement clickButton;
        if(isElementDisplayed(getByLocator(LBL_DATA_LIST, Header, "1"))) {
            if (sortType.equals("descending")) {
                clickButton = waitForElementToBeClickable(getByLocator(BTN_HEADER_SORT, Header));
                try {
                    clickButton.click();
                } catch (ElementClickInterceptedException e) {
                    clickButton.click();
                }
            } else if (sortType.equals("ascending")) {
                clickButton = waitForElementToBeClickable(getByLocator(BTN_HEADER_SORT, Header));
                try {
                    clickButton.click();
                    clickButton.click();
                } catch (ElementClickInterceptedException e) {
                    clickButton.click();
                    clickButton.click();
                }
            }
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
    public boolean verifySortOrderByString(ArrayList<String> list, String sortType) {
        if (sortType.equals("ascending"))
            for(int i = 0; i < list.size()-1; i++) {
                if (list.get(i + 1).compareToIgnoreCase(list.get(i)) < 0) {
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        else if (sortType.equals("descending")) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i + 1).compareToIgnoreCase(list.get(i)) > 0) {
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        }
        else
            for (int i = 0; i < list.size() - 1; i++) {
                if (!(list.get(i + 1).equals(list.get(i)))) {
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        return true;
    }
    public boolean verifySortOrderByDate(ArrayList<String> list, String sortType) throws ParseException {
        // format MM/dd/yyyy
        Date date2;
        Date date1;

        if (sortType.equals("ascending"))
            for(int i = 0; i < list.size()-1; i++) {
                date2 = new SimpleDateFormat("MM/dd/yyyy").parse(list.get(i+1));
                date1 = new SimpleDateFormat("MM/dd/yyyy").parse(list.get(i));
                if (date2.before(date1)) {
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        else if (sortType.equals("descending")) {
            for (int i = 0; i < list.size() - 1; i++) {
                date2 = new SimpleDateFormat("MM/dd/yyyy").parse(list.get(i+1));
                date1 = new SimpleDateFormat("MM/dd/yyyy").parse(list.get(i));
                if (date2.after(date1)) {
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifySortByHeader(String sortHeader, String sortType) throws InterruptedException, ParseException {
//        clickSortButton(sortHeader, sortType);
        int totalPage = getTotalPage();
        String index = "";
        switch (sortHeader) {
            case "Staff Code": index = "1"; break;
            case "Full Name": index = "2"; break;
            case "Joined Date": index = "4"; break;
            case "Type": index = "5"; break;
        }
        ArrayList<String> listOfSortValue = new ArrayList<>();
        boolean flag = false;
        switch(sortHeader) {
            case "Joined Date":
                for (int page = 1; page <= totalPage; page++) {
                    isElementDisplayed(getByLocator(LBL_DATA_LIST, sortHeader, index));
                    waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, sortHeader, index));
                    List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, sortHeader, index));
                    for (WebElement data : dataList) {
                        System.out.println(data.getText());
                        listOfSortValue.add(data.getText());
                    }
                    flag = verifySortOrderByDate(listOfSortValue, sortType);
                    System.out.println(flag);
                    if (totalPage > 1 && page < totalPage) {
                        try {
                            clickElement(BTN_NEXT_PAGE);
                        } catch (ElementClickInterceptedException e) {
                            clickElement(BTN_NEXT_PAGE);
                        }
                    }
                }
                System.out.println(listOfSortValue);
                break;
            default:
                for (int page = 1; page <= totalPage; page++) {
                    isElementDisplayed(getByLocator(LBL_DATA_LIST, sortHeader, index));
                    waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, sortHeader, index));
                    List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, sortHeader, index));
                    for (WebElement data : dataList) {
                        System.out.println(data.getText());
                        listOfSortValue.add(data.getText());
                    }
                    flag = verifySortOrderByString(listOfSortValue, sortType);
                    System.out.println(flag);
                    if (totalPage > 1 && page < totalPage) {
                        try {
                            clickElement(BTN_NEXT_PAGE);
                        } catch (ElementClickInterceptedException e) {
                            clickElement(BTN_NEXT_PAGE);
                        }
                    }
                }
                System.out.println(listOfSortValue);
            }
        return flag;
    }

    public boolean verifySortByFilter(String sortType) {
        clickElement(DDL_FILTER);
        clickElement(getByLocator(CHK_FILTER_TYPE,sortType));
        int totalPage = getTotalPage();
        ArrayList<String> listOfSortValue = new ArrayList<>();
        boolean flag = false;
        for (int page = 1; page <= totalPage; page++) {
            isElementDisplayed(getByLocator(LBL_DATA_LIST, "Type", "5"));
            waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, "Type", "5"));
            List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, "Type", "5"));
            for (WebElement data : dataList) {
                System.out.println(data.getText());
                listOfSortValue.add(data.getText());
            }
            flag = verifySortOrderByString(listOfSortValue, sortType);
            System.out.println(flag);
            if (totalPage > 1 && page < totalPage) {
                try {
                    clickElement(BTN_NEXT_PAGE);
                } catch (ElementClickInterceptedException e) {
                    clickElement(BTN_NEXT_PAGE);
                }
            }
        }
        System.out.println(listOfSortValue);
        return flag;
    }
    public void inputSearchCriteria(String text) {
        inputText(TXT_SEARCH_BAR, text);
    }
    public void clickSearchButton() {
        clickElement(BTN_SEARCH);
        isElementDisplayed(getByLocator(LBL_DATA_LIST, "Full Name", "2"));
    }
    public ArrayList<String> getTextOfListElement(By locator) {
        List<WebElement> Elements = waitForVisibilityOfAllElementsLocatedBy(locator);
        ArrayList<String> list = new ArrayList<>();
        for(WebElement element: Elements) {
            list.add(element.getText());
        }
        return list;
    }
    public boolean verifySearchCriteria(String searchText, String searchType) throws InterruptedException {
        String index = "";
        switch(searchType) {
            case "Staff Code": index = "1"; break;
            case "Full Name": index = "2"; break;
        }
        sleep(5000);
        if(isElementDisplayed(getByLocator(LBL_DATA_LIST, "Full Name", "2"))) {
            ArrayList<String> resultTextDataList = getTextOfListElement(getByLocator(LBL_DATA_LIST, searchType, index));
            for (String data :resultTextDataList) {
                System.out.println(data);
                if(!(data.contains(searchText)))
                    return false;
            }
        }
        return true;
    }
}
