package org.housecallpro.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewCustomerPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewCustomerPage.class);

    @FindBy(id = "customer-dialog-first-name")
    WebElement firstNameInputField;

    @FindBy(xpath = "//span[contains(text(), 'create')]")
    WebElement createCustomerButton;

    @FindBy(xpath = "//div//h2[contains(text(), 'Add new customer')]")
    WebElement addNewCustomerForm;

    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }

    public NewCustomerPage enterFirstName(String firstName) {
        LOGGER.info("entering first name [{}]", firstName);
        getWait().until(ExpectedConditions.visibilityOf(firstNameInputField));
        firstNameInputField.sendKeys(firstName);
        return this;
    }

    @SneakyThrows
    public NewJobPage clickCreateCustomerButton() {
        LOGGER.info("clicking [CREATE CUSTOMER] button");
        getWait().until(ExpectedConditions.visibilityOf(createCustomerButton));
        createCustomerButton.click();
        Thread.sleep(1000);
        return newInstance(NewJobPage.class);
    }

}
