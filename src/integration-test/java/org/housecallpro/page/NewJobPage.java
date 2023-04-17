package org.housecallpro.page;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.housecallpro.utils.PriceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.Keys.BACK_SPACE;

@Slf4j
public class NewJobPage extends BasePage {

    @FindBy(css = "button[data-testid='createJobContainer__saveBtn']")
    WebElement saveJobButton;

    @FindBy(xpath = "//span[contains(text(), '+ New customer')]")
    WebElement addNewCustomerButton;

    @FindBy(xpath = "//p[contains(text(), 'Private notes')]")
    WebElement privateNotesButton;
    @FindBy(css = "textarea[data-testid='private-notes-textfield']")
    WebElement privateNotesTextArea;

    @FindBy(id = "item-name")
    WebElement itemNameInputField;

    @FindBy(id = "unit-price")
    WebElement unitPriceInputField;

    @FindBy(id = "qty")
    WebElement quantityInputField;

    @FindBy(xpath = "//div//p[contains(text(), 'Total')]//following::div//h6//span[contains(text(), '$')]")
    WebElement totalPriceText;

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    @Step("Clicking [JOB SAVE] button")
    public JobPage clickSaveJobButton() {
        log.info("Clicking [SAVE JOB] button");
        getWait().until(ExpectedConditions.elementToBeClickable(saveJobButton));
        saveJobButton.click();
        return newInstance(JobPage.class);
    }

    @Step("Click [+ NEW CUSTOMER] button")
    public NewCustomerPage clickAddNewCustomer() {
        log.info("Clicking [+ NEW CUSTOMER] button");
        getWait().until(ExpectedConditions.visibilityOf(addNewCustomerButton));
        addNewCustomerButton.click();
        return newInstance(NewCustomerPage.class);
    }

    public NewJobPage clickPrivateNotes() {
        log.info("Clicking [Private notes] button");
        getWait().until(ExpectedConditions.visibilityOf(privateNotesButton));
        privateNotesButton.click();
        return this;
    }

    @Step("Entering private notes [{privateNotes}]")
    public NewJobPage enterPrivateNotes(String privateNotes) {
        clickPrivateNotes();
        log.info("Entering private notes [{}]", privateNotes);
        getWait().until(ExpectedConditions.visibilityOf(privateNotesTextArea));
        privateNotesTextArea.sendKeys(privateNotes);
        return this;
    }

    @Step("Entering item name [{itemName}]")
    public NewJobPage enterItemName(String itemName) {
        log.info("Entering item name [{}]", itemName);
        getWait().until(ExpectedConditions.visibilityOf(itemNameInputField));
        itemNameInputField.sendKeys(itemName);
        return this;
    }

    @Step("Entering Qty [{quantity}]")
    public NewJobPage enterQuantity(String quantity) {
        log.info("Entering Qty [{}]", quantity);
        getWait().until(ExpectedConditions.visibilityOf(itemNameInputField));
        quantityInputField.sendKeys(getSelectAllTextKeys(), BACK_SPACE);
        quantityInputField.sendKeys(quantity);

        String unitPrice = unitPriceInputField.getAttribute("value");
        String expectedTotalPrice = PriceUtils.calculateTotalPrice(quantity, unitPrice);
        getWait().until(ExpectedConditions.textToBePresentInElement(totalPriceText, expectedTotalPrice));

        return this;
    }

    public NewJobPage enterQuantity(int quantity) {
        return enterQuantity(String.valueOf(quantity));
    }

    @Step("Entering unit price [{unitPrice}]")
    public NewJobPage enterUnitPrice(String unitPrice) {
        log.info("Entering unit price [{}]", unitPrice);
        getWait().until(ExpectedConditions.visibilityOf(unitPriceInputField));
        unitPriceInputField.sendKeys(getSelectAllTextKeys(), BACK_SPACE);
        unitPriceInputField.sendKeys(unitPrice);

        String quantity = quantityInputField.getAttribute("value");
        String expectedTotalPrice = PriceUtils.calculateTotalPrice(quantity, unitPrice);
        getWait().until(ExpectedConditions.textToBePresentInElement(totalPriceText, expectedTotalPrice));

        return this;
    }

    public NewJobPage enterUnitPrice(int unitPrice) {
        return enterUnitPrice(String.valueOf(unitPrice));
    }

}
