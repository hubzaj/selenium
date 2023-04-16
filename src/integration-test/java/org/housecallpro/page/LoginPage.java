package org.housecallpro.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class LoginPage extends BasePage {

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
        log.info("setting email text field with value [{}]", email);
        getWait().until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(char[] password) {
        log.info("setting password text field");
        getWait().until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(new String(password));
        return this;
    }

    public HomePage clickSignInButton() {
        log.info("clicking [SIGN IN] button");
        getWait().until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
        return newInstance(HomePage.class);
    }

}
