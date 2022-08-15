package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.dataProvider.DataProviderSearch;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class VerifyAdminSortCriteriaTest extends BaseTest {
    ManageUserPage manageUserPage;
    LoginPage loginPage;
    HomePage homePage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;
    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        manageUserPage = new ManageUserPage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();
//        loginPage.loginWithDefaultAccount();
    }
    //Header = {"Staff Code", "Full Name", "Joined Date", "Type"} - sortType = {"ascending", "descending"}
    @Test(dataProvider = "sortHeaderUserData", dataProviderClass = DataProviderSearch.class)
    public void verifyAdminSearchByHeaderCriteria(String sortHeader, String sortType) throws InterruptedException, ParseException {
        loginPage.loginWithDefaultAccount();
        //

        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickSortButton(sortHeader, sortType);
        assertThat(
                "Verify sort order: ",
                manageUserPage.verifySortByHeader(sortHeader, sortType),
                equalTo(true)
        );
    }
    //Filter = {"All", "Admin", "Staff"}
    @Test(dataProvider = "filterUserData", dataProviderClass = DataProviderSearch.class)
    public void verifyAdminSearchByFilterCriteria(String filterType) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        //
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickFilterType(filterType);
        assertThat(
                "Verify filter result: ",
                manageUserPage.verifySortByFilter(filterType),
                equalTo(true)
        );
    }
    @Test(dataProvider = "searchData", dataProviderClass = DataProviderSearch.class)
    public void verifyAdminSearchBySearchBarCriteria(String searchData, String searchType) throws InterruptedException {
        loginPage.loginWithDefaultAccount();
        //

        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.inputSearchCriteria(searchData);
        manageUserPage.clickSearchButton();
        assertThat(
                "Verify search result: ",
                manageUserPage.verifySearchCriteria(searchData,searchType),
                containsString(searchData)
        );
    }
    @Test
    public void verifyAdminCanSeeUserDetailInformationSuccessfully() {
        loginPage.loginWithDefaultAccount();
        //

        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        manageUserPage.clickDetailRandomUser();

        assertThat(
                "verify Staff code label: ",
                detailInformationPage.getDetailInformationLabel("1"),
                equalTo("Staff Code")
        );

        assertThat(
                "verify Full Name label: ",
                detailInformationPage.getDetailInformationLabel("2"),
                equalTo("Full Name")
        );
        assertThat(
                "verify username label: ",
                detailInformationPage.getDetailInformationLabel("3"),
                equalTo("Username")
        );
        assertThat(
                "verify Date of birth label: ",
                detailInformationPage.getDetailInformationLabel("4"),
                equalTo("Date Of Birth")
        );
        assertThat(
                "verify type: ",
                detailInformationPage.getDetailInformationLabel("5"),
                equalTo("Gender")
        );
        assertThat(
                "verify type: ",
                detailInformationPage.getDetailInformationLabel("6"),
                equalTo("Joined Date")
        );
        assertThat(
                "verify type: ",
                detailInformationPage.getDetailInformationLabel("7"),
                equalTo("Type")
        );
        assertThat(
                "verify type: ",
                detailInformationPage.getDetailInformationLabel("8"),
                equalTo("Location")
        );
    }
}
