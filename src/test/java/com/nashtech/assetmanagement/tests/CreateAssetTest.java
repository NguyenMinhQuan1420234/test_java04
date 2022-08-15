package com.nashtech.assetmanagement.tests;

import com.nashtech.assetmanagement.dataProvider.DataProviderAsset;
import com.nashtech.assetmanagement.pages.*;
import com.nashtech.assetmanagement.pages.shared.DetailedInformationPage;
import com.nashtech.assetmanagement.pages.shared.ModalHandle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateAssetTest extends BaseTest {
    ManageAssetPage assetPage;
    LoginPage loginPage;
    HomePage homePage;
    DetailedInformationPage detailInformationPage;
    ModalHandle alertHandle;
    ManageAssetPage manageAssetPage;
    CreateAssetPage createAssetPage;

    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        detailInformationPage = new DetailedInformationPage();
        alertHandle = new ModalHandle();
        assetPage = new ManageAssetPage();
        createAssetPage = new CreateAssetPage();
        manageAssetPage = new ManageAssetPage();

//        loginPage.loginWithDefaultAccount();
//        alertHandle.closeAlert();
//        homePage.moveToPage("Manage Asset");
//        alertHandle.waitLoadingScreen();
//        manageAssetPage.clickAssetPageButton("Create new asset");



    }

    @Test(groups = {"1"},dataProvider = "assetData", dataProviderClass = DataProviderAsset.class)
    public void createAssetSuccessfully(String name, String category, String specification, String installedDate, String state) {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage Asset");
        alertHandle.waitLoadingScreen();
        manageAssetPage.clickAssetPageButton("Create new asset");
// Before
        createAssetPage.inputName(name);
        createAssetPage.selectCategory(category);
        createAssetPage.inputSpecification(specification);
        createAssetPage.inputInstalledDate(installedDate);
        createAssetPage.selectState(state);
        createAssetPage.clickButtonNewAssetPage("Save");
        alertHandle.waitLoadingScreen();
        manageAssetPage.clickDetailAssetInformation();

        assertThat(
                "verify asset Category",
                detailInformationPage.getDetailInformationValue("Category"),
                equalTo("Bluetooth Mouse")
        );
        assertThat(
                "verify asset Specification",
                detailInformationPage.getDetailInformationValue("Specification"),
                equalTo("An input specification")
        );
        assertThat(
                "verify asset InstalledDate",
                detailInformationPage.getDetailInformationValue("Installed Date"),
                equalTo("02/02/2022")
        );
        assertThat(
                "verify asset Category",
                detailInformationPage.getDetailInformationValue("Asset Name"),
                equalTo("Chuột Cống")
        );

        detailInformationPage.clickClose();
// After
        manageAssetPage.clickAssetDisableButton(1, "btnHighLight");
        alertHandle.clickModalButtonByText("Delete");

    }

    @Test
    public void verifySaveButtonIsNotEnable() {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage Asset");
        alertHandle.waitLoadingScreen();
        manageAssetPage.clickAssetPageButton("Create new asset");

        createAssetPage.inputName("Chuột Cống");

        assertThat(
                "verify save button status ",
                createAssetPage.isSaveButtonNotEnable(),
                equalTo(true)
        );
    }

    @Test(dataProvider = "assetNewCategoryData", dataProviderClass = DataProviderAsset.class)
    public void verifyCreateNewCategoryAndPrefixSuccessfully(String newCategoryName, String newCategoryPrefix) {
        loginPage.loginWithDefaultAccount();
        alertHandle.closeAlert();
        homePage.moveToPage("Manage Asset");
        alertHandle.waitLoadingScreen();
        manageAssetPage.clickAssetPageButton("Create new asset");

        createAssetPage.inputName("Chuột Cống");
        createAssetPage.clickAddNewCateGory();
        createAssetPage.inputNewCategoryName(newCategoryName);
        createAssetPage.inputNewCategoryPrefix(newCategoryPrefix);
        createAssetPage.clickAddIcon();

        assertThat(
                "verify add Message ",
                alertHandle.getAlertMessageText(),
                equalTo("SUCCESSFULLY ADDED!!")
        );
    }

//    @AfterMethod(onlyForGroups = {"1"})
//    public void cleanUpCreatedAsset() {
//        manageAssetPage.clickAssetDisableButton(1, "btnHighLight");
//        alertHandle.clickModalButtonByText("Delete");
//    }


}
