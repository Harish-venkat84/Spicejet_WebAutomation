package com.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.BaseClass;
import utils.JsonReader;

import java.util.Map;

public class LoginFunctionality extends BaseClass {

    Map<Object, Object> appData;

    @Given("After browser launch and navigate to home page user verify the application url")
    public void afterBrowserLaunchAndNavigateToHomePageUserVerifyTheApplicationUrl() {

        assertUrl("https://www.spicejet.com/");
    }

    @Given("user click the login anchor tag")
    public void userClickTheLoginAnchorTag() {

        clickElement(poManger.getHomePage().getLogin().get(0));
    }

    @Then("validate login card visible to user")
    public void validateLoginCardVisibleToUser() {

        assertString(poManger.getHomePage().getLogin().get(1), "Login");
    }

    @Given("user click the email radio button")
    public void userClickTheEmailRadioButton() {

        clickElement(poManger.getLoginCard().getEmailRadioBtn());
    }

    @Given("user enter the email id in the input field")
    public void userEnterTheEmailIdInTheInputField() {

        appData = JsonReader.jsonReader();

        sendKeys(poManger.getLoginCard().getEmailInputField(), getAsString(appData.get("emailId")));
    }

    @Given("user enter the password in the input field")
    public void userEnterThePasswordInTheInputField() {

        sendKeys(poManger.getLoginCard().getPasswordInputField(), getAsString(appData.get("password")));
    }

    @Given("user click the login button")
    public void userClickTheLoginButton() {

        clickElement(poManger.getLoginCard().getLoginBtn());
    }

    @Then("validate the user name")
    public void validateTheUserName() {

        threadSleep(3000);
        assertString(poManger.getHomePage().getUserName("Harish"), "Hi "+ getAsString(appData.get("userName")));
    }

    @Then("user click the logout button")
    public void userClickTheLogoutButton() {

        clickElement(poManger.getHomePage().getAccountDropdown().get(5));

        clickElement(poManger.getHomePage().getLogout());
    }

    // @loginWithInvalidCredentials

    @Given("user enter the email id {string} in the input field")
    public void userEnterTheEmailIdInTheInputField(String emailId) {

        sendKeys(poManger.getLoginCard().getEmailInputField(), emailId);
    }

    @Given("user enter the password {string} in the input field")
    public void userEnterThePasswordInTheInputField(String password) {

        sendKeys(poManger.getLoginCard().getPasswordInputField(), password);
    }

    @Then("verify the error message")
    public void verifyTheErrorMessage() {

        assertString(poManger.getHomePage().getLoginErrorMessage(), "Invalid Username/Password");
    }
}
