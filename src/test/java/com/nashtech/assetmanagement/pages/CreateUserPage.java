package com.nashtech.assetmanagement.pages;

import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CreateUserPage extends  BasePage {
    /** ------------------ Web Elements ----------------------*/
    private final By TXT_FIRSTNAME = By.id("firstName");
    private final By TXT_LASTNAME = By.id("lastName");
    private final By TXT_DATEOFBIRTH = By.id("dateOfBirth");
    private final By RDO_MALE = By.id("male");
    private final By RDO_FEMALE = By.id("female");
    private final By TXT_JOINDATE = By.id("joinedDate");
    private final By DDL_TYPE = By.id("cars");
    private final By OPT_ADMIN = By.xpath("//select[@id='cars']/option[1]");
    private final By OPT_STAFF = By.xpath("//select[@id='cars']/option[2]");
    private final By DDL_LOCATION = By.xpath("//label[text()='Location']/following-sibling::select");
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
        if(type.equals("ADMIN"))
            selectElementValue(DDL_TYPE,"ADMIN");
        else if (type.equals("STAFF"))
            selectElementValue(DDL_TYPE,"STAFF");
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
    public void clickButton(String text) {
        By BTN = By.id(String.format("%s", text));
        clickElement(BTN);
    }

    public void createUser(JsonObject user){
        inputFirstname(user.get("firstName").getAsString());
        inputLastname(user.get("lastName").getAsString());
        inputDateOfBirth(user.get("dateOfBirth").getAsString());
        selectGender(user.get("gender").getAsString());
        inputJoinDate(user.get("joinDate").getAsString());
        selectUserType(user.get("type").getAsString());
        clickButton("save");
    }

}
