@loginFunctionality
Feature: Validate Login Functionality

  Background: Navigate to Login page
    Given After browser launch and navigate to home page user verify the application url
    And user click the login anchor tag
    Then validate login card visible to user

  @loginWithValidCredentials
  Scenario: validate login with valid credentials
    Given user click the email radio button
    And user enter the email id in the input field
    And user enter the password in the input field
    And user click the login button
    Then validate the user name
    And user click the logout button

  @loginWithInvalidCredentials
  Scenario: validate login with invalid credentials
    Given user click the email radio button
    And user enter the email id "demo@gmail.com" in the input field
    And user enter the password "password@123" in the input field
    And user click the login button
    Then verify the error message
