package com.nashtech.assetmanagement.tests;

import com.google.gson.JsonObject;
import com.nashtech.assetmanagement.dataProvider.DataProviderUser;
import com.nashtech.assetmanagement.pages.CreateUserPage;
import com.nashtech.assetmanagement.pages.HomePage;
import com.nashtech.assetmanagement.pages.LoginPage;
import com.nashtech.assetmanagement.pages.ManageUserPage;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.*;

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

    @BeforeMethod()
    public void precondition() {
        loginPage = new LoginPage();
        createUserPage = new CreateUserPage();
        editUserPage = new CreateUserPage();
        homePage = new HomePage();
        manageUserPage = new ManageUserPage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();

//        loginPage.loginWithDefaultAccount();
//        alertHandle.closeAlert();
//        homePage.moveToPage("Manage User");
//        homePage.waitLoadingScreen();
//        manageUserPage.clickCreateNewUserButton();
//        createUserPage.createUserCustomInput("deptrai","hailua","22/02/2000","Female","22/02/2022","ADMIN");
    }

    @Test(dataProvider = "editUsetData", dataProviderClass = DataProviderUser.class, groups = {"1"})
    public void editUserSuccessfully(String dateOfBirth, String gender, String joinedDate, String type)  {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUserCustomInput("deptrai","hailua","22/02/2000","Female","22/02/2022","ADMIN");
// bf
        manageUserPage.clickFirstUserButton("edit");
        alertHandle.waitLoadingScreen();
        editUserPage.inputDateOfBirth(dateOfBirth);
        editUserPage.selectGender(gender);
        editUserPage.inputJoinDate(joinedDate);
        editUserPage.selectUserType(type);
        editUserPage.clickSaveButton();
        alertHandle.waitLoadingScreen();
        manageUserPage.clickDetailFirstUser();

        assertThat(
                "verify User Date of Birth:",
                detailInformationPage.getDetailInformationValue("Date Of Birth"),
                equalTo("09/09/2000")
        );
        assertThat(
                "verify User gender:",
                detailInformationPage.getDetailInformationValue("Gender"),
                equalTo("Female")
        );
        assertThat(
                "verify User Join date:",
                detailInformationPage.getDetailInformationValue("Joined Date"),
                equalTo("09/09/2022")
        );
        assertThat(
                "verify User Type:",
                detailInformationPage.getDetailInformationValue("Type"),
                equalTo("STAFF")
        );
        detailInformationPage.clickClose();
        //
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("disable-button");
    }

    @Test(dataProvider = "editUsetData", dataProviderClass = DataProviderUser.class, groups = {"1"})
    public void verifyEditUserCannotEditNameSuccessfully(String dateOfBirth, String gender, String joinedDate, String type) {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUserCustomInput("deptrai","hailua","22/02/2000","Female","22/02/2022","ADMIN");
        //bf

        manageUserPage.clickFirstUserButton("edit");
        alertHandle.waitLoadingScreen();
        editUserPage.inputDateOfBirth(dateOfBirth);
        editUserPage.selectGender(gender);
        editUserPage.inputJoinDate(joinedDate);
        editUserPage.selectUserType(type);

        assertThat(
                "verify first name field that can not be edit: ",
                editUserPage.isFieldEdited("First Name"),
                equalTo(true)
        );
        assertThat(
                "verify last name field that can not be edit: ",
                editUserPage.isFieldEdited("Last Name"),
                equalTo(true)
        );

        editUserPage.clickSaveButton();
        //af
        manageUserPage.clickFirstUserButton("disable");
        alertHandle.clickModalButtonById("disable-button");
    }

    @Test
    public void verifyEditUserClickCancelButtonSuccessfully() {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage User");
        homePage.waitLoadingScreen();
        manageUserPage.clickCreateNewUserButton();
        createUserPage.createUserCustomInput("deptrai","hailua","22/02/2000","Female","22/02/2022","ADMIN");
        //bf

        manageUserPage.clickFirstUserButton("edit");
        alertHandle.waitLoadingScreen();
        editUserPage.clickButton("cancel");

        assertThat(
                "verify name of Page: ",
                homePage.getCurrentPageName(),
                equalTo("Manage User")
        );

    }

//    @AfterMethod(onlyForGroups = {"1"})
//    public void disableCreatedUser() {
//        manageUserPage.clickFirstUserButton("disable");
//        alertHandle.clickModalButtonById("disable-button");
//    }
}
