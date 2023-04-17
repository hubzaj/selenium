package org.housecallpro.page;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class NewCustomerPage extends BasePage {

    @FindBy(id = "customer-dialog-first-name")
    WebElement firstNameInputField;

    @FindBy(xpath = "//span[contains(text(), 'create')]")
    WebElement createCustomerButton;

    @FindBy(xpath = "//div//h2[contains(text(), 'Add new customer')]")
    WebElement addNewCustomerForm;

    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Entering first name {firstName}")
    public NewCustomerPage enterFirstName(String firstName) {
        log.info("Entering first name [{}]", firstName);
        getWait().until(ExpectedConditions.visibilityOf(firstNameInputField));
        firstNameInputField.sendKeys(firstName);
        return this;
    }

    @Step("Click [CREATE CUSTOMER] button")
    @SneakyThrows
    public NewJobPage clickCreateCustomerButton() {
        log.info("Clicking [CREATE CUSTOMER] button");
        getWait().until(ExpectedConditions.visibilityOf(createCustomerButton));
        createCustomerButton.click();
        // TODO: Fix below wait as it's too time consuming and remove [Thread.sleep(1000)]
        // getWait().until(ExpectedConditions.invisibilityOf(addNewCustomerForm));
        Thread.sleep(1000);
        return newInstance(NewJobPage.class);
    }

}
