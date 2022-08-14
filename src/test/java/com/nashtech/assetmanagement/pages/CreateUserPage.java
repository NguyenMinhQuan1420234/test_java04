package com.nashtech.assetmanagement.pages;

import com.google.gson.JsonObject;
import org.openqa.selenium.By;

public class CreateUserPage extends  BasePage {
    /** ------------------ Web Elements ----------------------*/
    private final By TXT_FIRSTNAME = By.id("firstName");
    private final By TXT_LASTNAME = By.id("lastName");
    private final By TXT_DATEOFBIRTH = By.id("dateOfBirth");
    private final By RDO_MALE = By.id("male");
    private final By RDO_FEMALE = By.id("female");
    private final By TXT_JOINDATE = By.id("joinedDate");
    private final By DDL_TYPE = By.id("cars");
    private final By DDL_LOCATION = By.xpath("//label[text()='Location']/following-sibling::select");
    private  final By BTN_SAVE = By.id("save");
    /** -------------------- Page Methods ---------------------*/
    public void inputFirstname(String firstname) {
        inputText(TXT_FIRSTNAME, firstname);
    }

    public void inputLastname(String lastname) {
        inputText(TXT_LASTNAME, lastname);
    }

    public void inputDateOfBirth(String birthDate) {
        inputText(TXT_DATEOFBIRTH, birthDate);
    }

    public void inputJoinDate(String joinDate) {
        inputText(TXT_JOINDATE, joinDate);
    }

    public void selectGender(String gender) {
        if (gender.equals("Male"))
            clickElement(RDO_MALE);
        else
            clickElement(RDO_FEMALE);
    }

    public void selectUserType(String type) {
        clickElement(DDL_TYPE);
        selectElementValue(DDL_TYPE,type);
    }

    public void selectAdminLocation(String location) {
        clickElement(DDL_LOCATION);
        if(location.equals("Ho Chi Minh"))
            selectElementValue(DDL_LOCATION, "Ho Chi Minh");
        else if (location.equals("Da Nang"))
            selectElementValue(DDL_LOCATION, "Da Nang");
        else if (location.equals("Ha Noi")) {
            selectElementValue(DDL_LOCATION, "Ha Noi");

        }
    }
    public void clickSaveButton() {
        clickElement(BTN_SAVE);
    }
    public void clickButton(String text) {
        By BTN = By.id(String.format("%s", text));
        clickElement(BTN);
    }

    public void createUserDefault(JsonObject user){
        inputFirstname(user.get("firstName").getAsString());
        inputLastname(user.get("lastName").getAsString());
        inputDateOfBirth(user.get("dateOfBirth").getAsString());
        selectGender(user.get("gender").getAsString());
        inputJoinDate(user.get("joinDate").getAsString());
        selectUserType(user.get("type").getAsString());
        clickButton("save");
    }
    public void createUserCustomInput(String firstName, String lastName, String dateOfBirth, String gender, String joinDate, String type){
        inputFirstname(firstName);
        inputLastname(lastName);
        inputDateOfBirth(dateOfBirth);
        selectGender(gender);
        inputJoinDate(joinDate);
        selectUserType(type);
        clickButton("save");
    }

    public boolean isFieldEdited(String textFieldName) {
        if (textFieldName.equals("First Name"))
            return !(waitForVisibilityOfElementLocated(TXT_FIRSTNAME).isEnabled());
        else if(textFieldName.equals("Last Name"))
            return !(waitForVisibilityOfElementLocated(TXT_FIRSTNAME).isEnabled());
        return false;
    }

}
