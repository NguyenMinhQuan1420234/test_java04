package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
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
        loginPage.loginWithDefaultAccount();
    }
    //Header = {"Staff Code", "Full Name", "Joined Date", "Type"} - sortType = {"ascending", "descending"}
    @Test
    public void verifyAdminSearchByHeaderCriteria() throws InterruptedException, ParseException {
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickSortButton("Joined Date", "descending");
        assertThat(
                "Verify sort order: ",
                manageUserPage.verifySortByHeader("Joined Date", "descending"),
                equalTo(true)
        );
    }
    //Filter = {"All", "Admin", "Staff"}
    @Test
    public void verifyAdminSearchByFilterCriteria() throws InterruptedException {
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickFilterType("Staff");
        assertThat(
                "Verify sort order: ",
                manageUserPage.verifySortByFilter("Staff"),
                equalTo(true)
        );
    }
    @Test
    public void verifyAdminSearchBySearchBarCriteria() throws InterruptedException {
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.inputSearchCriteria("SD00");
        manageUserPage.clickSearchButton();
        assertThat(
                "Verify sort order: ",
                manageUserPage.verifySearchCriteria("SD00","Staff Code"),
                equalTo(true)
        );
    }
    @Test
    public void verifyAdminCanSeeUserDetailInformationSuccessfully() {
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
