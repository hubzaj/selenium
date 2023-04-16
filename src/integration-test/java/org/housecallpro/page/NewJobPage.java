package org.housecallpro.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.Keys.BACK_SPACE;

public class NewJobPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewJobPage.class);

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

    public JobPage clickSaveJobButton() {
        LOGGER.info("clicking [SAVE JOB] button");
        getWait().until(ExpectedConditions.elementToBeClickable(saveJobButton));
        saveJobButton.click();
        return newInstance(JobPage.class);
    }

    public NewCustomerPage clickAddNewCustomer() {
        LOGGER.info("clicking [+ NEW CUSTOMER] button");
        getWait().until(ExpectedConditions.visibilityOf(addNewCustomerButton));
        addNewCustomerButton.click();
        return newInstance(NewCustomerPage.class);
    }

    public NewJobPage clickPrivateNotes() {
        LOGGER.info("clicking [Private notes] button");
        getWait().until(ExpectedConditions.visibilityOf(privateNotesButton));
        privateNotesButton.click();
        return this;
    }

    public NewJobPage enterPrivateNotes(String privateNotes) {
        clickPrivateNotes();
        LOGGER.info("entering private notes [{}]", privateNotes);
        getWait().until(ExpectedConditions.visibilityOf(privateNotesTextArea));
        privateNotesTextArea.sendKeys(privateNotes);
        return this;
    }

    @SneakyThrows
    public NewJobPage enterItemName(String itemName) {
        LOGGER.info("entering item name [{}]", itemName);
        Thread.sleep(1000);
//        getWait().until(ExpectedConditions.visibilityOf(itemNameInputField));
        itemNameInputField.sendKeys(itemName);
        return this;
    }

    public NewJobPage enterQuantity(String quantity) {
        LOGGER.info("entering Qty [{}]", quantity);
//        getWait().until(ExpectedConditions.visibilityOf(itemNameInputField));
        quantityInputField.sendKeys(getSelectAllTextKeys(), BACK_SPACE);
        quantityInputField.sendKeys(quantity);
        return this;
    }

    public NewJobPage enterQuantity(int quantity) {
        return enterQuantity(String.valueOf(quantity));
    }

    public NewJobPage enterUnitPrice(String unitPrice) {
        LOGGER.info("entering unit price [{}]", unitPrice);
//        getWait().until(ExpectedConditions.visibilityOf(unitPriceInputField));
        unitPriceInputField.sendKeys(getSelectAllTextKeys(), BACK_SPACE);
        unitPriceInputField.sendKeys(unitPrice);
        getWait().until(ExpectedConditions.textToBePresentInElement(totalPriceText, "$400.00"));
        return this;
    }

    public NewJobPage enterUnitPrice(int unitPrice) {
        return enterUnitPrice(String.valueOf(unitPrice));
    }

}
