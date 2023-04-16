package org.housecallpro.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(id = "email")
    WebElement emailTextField;

    @FindBy(id = "password")
    WebElement passwordTextField;

    @FindBy(xpath = "//span[contains(text(), 'Sign in')]")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        LOGGER.info("setting email text field with value [{}]", email);
        getWait().until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(char[] password) {
        LOGGER.info("setting password text field");
        getWait().until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(new String(password));
        return this;
    }

    public HomePage clickSignInButton() {
        LOGGER.info("clicking [SIGN IN] button");
        getWait().until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
        return newInstance(HomePage.class);
    }

}
