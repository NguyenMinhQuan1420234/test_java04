package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.nashtech.assetmanagement.utils.DateUtil.convertDateStringToDateStringByFormat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {
    LoginPage loginPage;
    CreateUserPage createUserPage;
    HomePage homePage;
    ManageUserPage manageUserPage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;

    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        createUserPage = new CreateUserPage();
        homePage = new HomePage();
        manageUserPage = new ManageUserPage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();
//        loginPage.loginWithDefaultAccount();
//        alertHandle.closeAlert();
    }

    @Test(dataProvider = "createUserWithAdminAccount", dataProviderClass = DataProviderUser.class, groups = {"1"})
    public void createUserSuccessfully(JsonObject user) {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        // Before
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.inputFirstname(user.get("firstName").getAsString());
        createUserPage.inputLastname(user.get("lastName").getAsString());
        createUserPage.inputDateOfBirth(user.get("dateOfBirth").getAsString());
        createUserPage.selectGender(user.get("gender").getAsString());
        createUserPage.inputJoinDate(user.get("joinDate").getAsString());
        createUserPage.selectUserType(user.get("type").getAsString());
        createUserPage.clickSaveButton();

        assertThat(
                "verify create message: ",
                alertHandle.getAlertMessageText(),
                equalTo("Successfully added!!")
        );

        alertHandle.closeAlert();
        // After
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("disable-button");
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class, groups = {"1"})
    public void verifyNewUserAddedToTopOfUserListSuccessfully(JsonObject user) {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        // before
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.inputFirstname(user.get("firstName").getAsString());
        createUserPage.inputLastname(user.get("lastName").getAsString());
        createUserPage.inputDateOfBirth(user.get("dateOfBirth").getAsString());
        createUserPage.selectGender(user.get("gender").getAsString());
        createUserPage.inputJoinDate(user.get("joinDate").getAsString());
        createUserPage.selectUserType(user.get("type").getAsString());
        createUserPage.clickButton("save");

        manageUserPage.clickDetailFirstUser();

        assertThat(
                "verify firstname: ",
                (user.get("firstName").getAsString() +" "+ user.get("lastName").getAsString()),
                equalTo(detailInformationPage.getDetailInformationValue("Full Name"))
        );

        assertThat(
                "verify date of birth: ",
                convertDateStringToDateStringByFormat(user.get("dateOfBirth").getAsString(), "MM/dd/yyyy"),
                equalTo(detailInformationPage.getDetailInformationValue("Date Of Birth"))
        );
        assertThat(
                "verify gender: ",
                user.get("gender").getAsString(),
                equalTo(detailInformationPage.getDetailInformationValue("Gender"))
        );
        assertThat(
                "verify join date: ",
                convertDateStringToDateStringByFormat(user.get("joinDate").getAsString(),"MM/dd/yyyy"),
                equalTo(detailInformationPage.getDetailInformationValue("Joined Date"))
        );
        assertThat(
                "verify type: ",
                user.get("type").getAsString(),
                equalTo(detailInformationPage.getDetailInformationValue("Type"))
        );

        detailInformationPage.clickClose();
        //after
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("disable-button");

    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyClickCancelButtonBackToManageUserPage(JsonObject user) {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        // before
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.inputFirstname(user.get("firstName").getAsString());
        createUserPage.inputLastname(user.get("lastName").getAsString());
        createUserPage.inputDateOfBirth(user.get("dateOfBirth").getAsString());
        createUserPage.selectGender(user.get("gender").getAsString());
        createUserPage.inputJoinDate(user.get("joinDate").getAsString());
        createUserPage.selectUserType(user.get("type").getAsString());
        createUserPage.clickButton("cancel");

        assertThat(
                "verify cancel button: ",
                manageUserPage.getPageTitle(),
                equalTo("User List")
        );
    }
//    @AfterMethod(onlyForGroups = {"1"})
//    public void disableCreatedUser() {
//        manageUserPage.clickFirstUserButton("disable");
//        alertHandle.clickModalButtonById("disable-button");
//    }
}
