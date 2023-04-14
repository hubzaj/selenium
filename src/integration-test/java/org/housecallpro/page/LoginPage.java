package org.housecallpro.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

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
        logger.info("setting email text field with value [{}]", email);
        emailTextField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        logger.info("setting password text field with value [{}]", password);
        passwordTextField.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInButton() {
        logger.info("clicking [SIGN IN] button");
        signInButton.click();
        return this;
    }

}