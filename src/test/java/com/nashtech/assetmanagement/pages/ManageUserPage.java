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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class ManageUserPage extends BasePage{
    /** ------------------ Web Elements ----------------------*/
    private final By BTN_CREATE_NEW_USER = By.xpath("//button[text()='Create new user']");
    private final By NOF_LOADING = By.xpath("//div[@id='NotiflixLoadingWrap']/div");
    private final By FIRST_USER = By.xpath("//tbody/tr[1]");
    private final By FIRST_USER_NAME = By.xpath("//tbody/tr[1]/td[2]");
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
    private final Pair<String, String> BTN_EDIT_USER = Pair.of("xpath", "//tr/td[text()='%s']/following-sibling::td/button[@id='btnEdit']");
    private final Pair<String, String> BTN_DISABLE_USER = Pair.of("xpath", "//tr/td[text()='%s']/following-sibling::td/button[@id='btnHighlight']");
    private final Pair<String, String> BTN_EDIT_DISABLE_USER = Pair.of("xpath", "//tr/td[text()='%s']/following-sibling::td/button[@id='%s']");

    /** -------------------- Page Methods ---------------------*/

    public void clickCreateNewUserButton() {
        try {
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
//        waitForStalenessOfElementLocated(findElement(NOF_LOADING));
        String number = Integer.toString((int)(Math.random()*5 + 1));
        clickElement(By.xpath(String.format(RANDOM_USER, number)), true);
    }
    public void isSearchCriteriaMatchExpected() {
        int totalPage = parseInt(getText(BTN_LAST_PAGE));

    }
    //Header = {"Staff Code", "Full Name", "Joined Date", "Type"} - sortType = {"ascending", "descending"}
    public void clickSortButton(String Header, String sortType) throws InterruptedException {
        WebElement clickButton= waitForElementToBeClickable(getByLocator(BTN_HEADER_SORT, Header));;
        if(isElementDisplayed(getByLocator(LBL_DATA_LIST, Header, "1"))) {
            if (sortType.equals("ascending")) {
                    clickButton.click();
            }
            else if (sortType.equals("descending")) {
                    clickButton.click();
                    clickButton.click();
            }
        }
        sleep(3000);
    }
    public int getTotalPage() {
        try {
            return parseInt(getText(BTN_LAST_PAGE));
        }
        catch(TimeoutException e) {
            return 1;
        }
    }
    public boolean verifySortOrderByString(ArrayList<String> list, String sortType) {
        if (sortType.equals("ascending"))
            for(int i = 0; i < list.size()-1; i++) {
                if (list.get(i + 1).compareToIgnoreCase(list.get(i)) < 0) {
                    System.out.println("fail at");
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        else if (sortType.equals("descending")) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i + 1).compareToIgnoreCase(list.get(i)) > 0) {
                    System.out.println("fail at");
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        }
        else
            for (int i = 0; i < list.size() - 1; i++) {
                if (!(list.get(i + 1).equals(list.get(i)))) {
                    System.out.println("fait at");
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
                    System.out.println("fait at");
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
                    System.out.println("fait at");
                    System.out.println(list.get(i + 1));
                    System.out.println(list.get(i));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifySortByHeader(String sortHeader, String sortType) throws InterruptedException, ParseException {
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
        if ("Joined Date".equals(sortHeader)) {
            for (int page = 1; page <= totalPage; page++) {
                isElementDisplayed(getByLocator(LBL_DATA_LIST, sortHeader, index));
                waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, sortHeader, index));
                List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, sortHeader, index));
                for (WebElement data : dataList) {
                    System.out.println(data.getText());
                    listOfSortValue.add(data.getText());
                }
                if ((!verifySortOrderByDate(listOfSortValue, sortType)))
                    break;
                else
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
        } else {
            for (int page = 1; page <= totalPage; page++) {
                isElementDisplayed(getByLocator(LBL_DATA_LIST, sortHeader, index));
                waitForVisibilityOfElementLocated(getByLocator(LBL_DATA_LIST, sortHeader, index));
                List<WebElement> dataList = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, sortHeader, index));
                for (WebElement data : dataList) {
                    System.out.println(data.getText());
                    listOfSortValue.add(data.getText());
                }
                if ((!verifySortOrderByString(listOfSortValue, sortType)))
                    break;
                else
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
    public void clickFilterType(String filterType) {
        List<WebElement> staffCodeList1 = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST,"Staff Code","1"));
        ArrayList<String> staffCodeData1 = new ArrayList<>();
        ArrayList<String> staffCodeData2 = new ArrayList<>();
        for (WebElement staffCode: staffCodeList1) {
            staffCodeData1.add(staffCode.getText());
        }

        clickElement(DDL_FILTER);
        clickElement(getByLocator(CHK_FILTER_TYPE,filterType));

        List<WebElement> staffCodeList2;
        boolean flag = true;
        while(flag) {
            staffCodeList2 = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, "Staff Code", "1"));
            for (WebElement staffCode: staffCodeList2) {
                staffCodeData2.add(staffCode.getText());
            }
            if(!(staffCodeData1.equals(staffCodeData2))) {
                flag = false;
            }
        }
    }

    public boolean verifySortByFilter(String filterType) {
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
            flag = verifySortOrderByString(listOfSortValue, filterType);
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
    public void waitForUserAppear(String name) throws InterruptedException {
        waitForTextToBePresentInElementLocated(FIRST_USER_NAME, name);
    }
    public ArrayList<String> getTextOfListElement(By locator) {
        List<WebElement> Elements = waitForVisibilityOfAllElementsLocatedBy(locator);
        ArrayList<String> list = new ArrayList<>();
        for(WebElement element: Elements) {
            list.add(element.getText());
        }
        return list;
    }
    public String verifySearchCriteria(String searchText, String searchType) throws InterruptedException {
        String index = "";
        switch(searchType) {
            case "Staff Code": index = "1"; break;
            case "Full Name": index = "2"; break;
        }
        sleep(3000);
        String searchResult = "";
        if(isElementDisplayed(getByLocator(LBL_DATA_LIST, "Full Name", "2"))) {
            ArrayList<String> resultTextDataList = getTextOfListElement(getByLocator(LBL_DATA_LIST, searchType, index));
            for (String data :resultTextDataList) {
                System.out.println(data);
                if(!(data.contains(searchText)))
                    return null;
                else
                    searchResult =  data;
            }
        }
        return searchResult;
    }
    /** -------------------- Click Edit/Disable User Methods ---------------------*/
    public void clickDisableUserButton() {
        List<WebElement> listOfUser = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, "Staff Code", "1"));
        String createdUserStaffCode = listOfUser.get(0).getText();
        clickElement(getByLocator(BTN_DISABLE_USER,createdUserStaffCode));
    }
    public void clickFirstUserButton(String buttonType) {
        List<WebElement> listOfUser = waitForVisibilityOfAllElementsLocatedBy(getByLocator(LBL_DATA_LIST, "Staff Code", "1"));
        String createdUserStaffCode = listOfUser.get(0).getText();
        if(buttonType.equals("edit"))
            clickElement(getByLocator(BTN_EDIT_DISABLE_USER,createdUserStaffCode,"btnEdit"));
        else if(buttonType.equals("disable"))
            clickElement(getByLocator(BTN_EDIT_DISABLE_USER,createdUserStaffCode,"btnHighlight"));
    }

    public void clickUserButtonByStaffCode(String buttonType, String staffCode) {
        if(buttonType.equals("edit"))
            clickElement(getByLocator(BTN_EDIT_DISABLE_USER,staffCode,"btnEdit"));
        else if(buttonType.equals("disable"))
            clickElement(getByLocator(BTN_EDIT_DISABLE_USER,staffCode,"btnHighlight"));
    }

}
