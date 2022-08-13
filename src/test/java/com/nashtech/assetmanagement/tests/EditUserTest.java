package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.CreateUserPage;
import com.nashtech.assetmanagement.pages.HomePage;
import com.nashtech.assetmanagement.pages.LoginPage;
import com.nashtech.assetmanagement.pages.ManageUserPage;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditUserTest extends BaseTest {
    LoginPage loginPage;
    CreateUserPage createUserPage;
    CreateUserPage editUserPage;
    HomePage homePage;
    ManageUserPage manageUserPage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;

    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        createUserPage = new CreateUserPage();
        editUserPage = new CreateUserPage();
        homePage = new HomePage();
        manageUserPage = new ManageUserPage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();

        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
    }

    @Test(dataProvider = "createUserWithAdminAccount", dataProviderClass = DataProviderUser.class, groups = {"1"})
    public void editUserSuccessfully(JsonObject user) {
        createUserPage.createUser(user);
        manageUserPage.clickFirstUserButton("edit");
        alertHandle.waitLoadingScreen();
        editUserPage.inputDateOfBirth("09/09/2000");
        editUserPage.selectGender("Female");
        editUserPage.inputJoinDate("09/09/2022");
        editUserPage.selectUserType("STAFF");
        editUserPage.clickSaveButton();
        alertHandle.waitLoadingScreen();
        manageUserPage.clickDetailFirstUser();

        assertThat(
                "verify User Date of Birth:",
                detailInformationPage.getUserDetail("Date Of Birth"),
                equalTo("09/09/2000")
        );
        assertThat(
                "verify User gender:",
                detailInformationPage.getUserDetail("Gender"),
                equalTo("Female")
        );
        assertThat(
                "verify User Join date:",
                detailInformationPage.getUserDetail("Joined Date"),
                equalTo("09/09/2022")
        );
        assertThat(
                "verify User Type:",
                detailInformationPage.getUserDetail("Type"),
                equalTo("STAFF")
        );
        detailInformationPage.clickClose();
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class, groups = {"1"})
    public void verifyEditUserCannotEditNameSuccessfully(JsonObject user) {
        createUserPage.createUser(user);
        manageUserPage.clickFirstUserButton("edit");
        alertHandle.waitLoadingScreen();
        editUserPage.inputDateOfBirth("08/09/2000");
        editUserPage.selectGender("Female");
        editUserPage.inputJoinDate("09/09/2022");
        editUserPage.selectUserType("STAFF");

        assertThat(
                "verify first name field that can not be edit: ",
                editUserPage.fieldCannotBeEdited("First Name"),
                equalTo(true)
        );
        assertThat(
                "verify last name field that can not be edit: ",
                editUserPage.fieldCannotBeEdited("Last Name"),
                equalTo(true)
        );

        editUserPage.clickSaveButton();

    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyEditUserClickCancelButtonSuccessfully(JsonObject user) {
        createUserPage.createUser(user);
        manageUserPage.clickFirstUserButton("edit");
        alertHandle.waitLoadingScreen();
        editUserPage.clickButton("cancel");

        assertThat(
                "verify name of Page: ",
                homePage.getCurrentPageName(),
                equalTo("Manage User")
        );

    }

    @AfterMethod(onlyForGroups = {"1"})
    public void disableCreatedUser() {
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButton("disable-button");
    }
}
