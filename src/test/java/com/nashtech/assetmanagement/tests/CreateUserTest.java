package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.AlertHandle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.nashtech.assetmanagement.utils.DateUtil.convertDateStringToDateStringByFormat;
import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {
    LoginPage loginPage;
    CreateUserPage createUserPage;
    NavigationPage navigationPage;
    ManageUserPage manageUserPage;
    DetailInformationPage detailInformationPage;
    AlertHandle alertHandle;

    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        createUserPage = new CreateUserPage();
        navigationPage = new NavigationPage();
        manageUserPage = new ManageUserPage();
        detailInformationPage = new DetailInformationPage();
        alertHandle = new AlertHandle();
        loginPage.loginWithAdminAccount();
    }

    @Test(dataProvider = "createUserWithAdminAccount", dataProviderClass = DataProviderUser.class)
    public void createUserSuccessfully(JsonObject user) {

        navigationPage.moveToPage("Manage User");
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
                alertHandle.getPopupMessageText(alertHandle.createMsg),
                equalTo("Successfully added!!")
        );

        manageUserPage.clickDetailFirstUser();

        assertThat(
                "verify firstname: ",
                (user.get("firstName").getAsString() +" "+ user.get("lastName").getAsString()),
                equalTo(detailInformationPage.getTextOfUserLabel("Full Name"))
        );
        assertThat(
                "verify date of birth: ",
                convertDateStringToDateStringByFormat(user.get("dateOfBirth").getAsString(), "MM/dd/yyyy"),
                equalTo(detailInformationPage.getTextOfUserLabel("Date Of Birth"))
        );
        assertThat(
                "verify gender: ",
                user.get("gender").getAsString(),
                equalTo(detailInformationPage.getTextOfUserLabel("Gender"))
        );
        assertThat(
                "verify join date: ",
                convertDateStringToDateStringByFormat(user.get("joinDate").getAsString(),"MM/dd/yyyy"),
                equalTo(detailInformationPage.getTextOfUserLabel("Joined Date")));
        assertThat(
                "verify type: ",
                user.get("type").getAsString(),
                equalTo(detailInformationPage.getTextOfUserLabel("Type")));
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyNewUserAddedToTopOfUserListSuccessfully(JsonObject user) {

        navigationPage.moveToPage("Manage User");
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
                equalTo(detailInformationPage.getTextOfUserLabel("Full Name"))
        );

        assertThat(
                "verify date of birth: ",
                convertDateStringToDateStringByFormat(user.get("dateOfBirth").getAsString(), "MM/dd/yyyy"),
                equalTo(detailInformationPage.getTextOfUserLabel("Date Of Birth"))
        );
        assertThat(
                "verify gender: ",
                user.get("gender").getAsString(),
                equalTo(detailInformationPage.getTextOfUserLabel("Gender"))
        );
        assertThat(
                "verify join date: ",
                convertDateStringToDateStringByFormat(user.get("joinDate").getAsString(),"MM/dd/yyyy"),
                equalTo(detailInformationPage.getTextOfUserLabel("Joined Date"))
        );
        assertThat(
                "verify type: ",
                user.get("type").getAsString(),
                equalTo(detailInformationPage.getTextOfUserLabel("Type"))
        );
    }

    @Test(dataProvider = "createUserAccount", dataProviderClass = DataProviderUser.class)
    public void verifyClickCancelButtonBackToManageUserPage(JsonObject user) {

        navigationPage.moveToPage("Manage User");
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
                manageUserPage.getPageLabelText(),
                equalTo("User List")
        );
    }

}
