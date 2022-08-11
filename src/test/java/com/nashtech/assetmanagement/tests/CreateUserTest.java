package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
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
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
    }

    @Test(dataProvider = "createUserWithAdminAccount", dataProviderClass = DataProviderUser.class)
    public void createUserSuccessfully(JsonObject user) {
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

        assertThat(
                "verify create message: ",
                alertHandle.getPopupMessageText(),
                equalTo("Successfully added!!")
        );

        alertHandle.closeAlert();
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyNewUserAddedToTopOfUserListSuccessfully(JsonObject user) {
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
                equalTo(detailInformationPage.getUserDetail("Full Name"))
        );

        assertThat(
                "verify date of birth: ",
                convertDateStringToDateStringByFormat(user.get("dateOfBirth").getAsString(), "MM/dd/yyyy"),
                equalTo(detailInformationPage.getUserDetail("Date Of Birth"))
        );
        assertThat(
                "verify gender: ",
                user.get("gender").getAsString(),
                equalTo(detailInformationPage.getUserDetail("Gender"))
        );
        assertThat(
                "verify join date: ",
                convertDateStringToDateStringByFormat(user.get("joinDate").getAsString(),"MM/dd/yyyy"),
                equalTo(detailInformationPage.getUserDetail("Joined Date"))
        );
        assertThat(
                "verify type: ",
                user.get("type").getAsString(),
                equalTo(detailInformationPage.getUserDetail("Type"))
        );
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyClickCancelButtonBackToManageUserPage(JsonObject user) {
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

}
