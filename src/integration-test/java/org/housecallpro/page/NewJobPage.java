package org.housecallpro.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.Keys.*;

public class NewJobPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NewJobPage.class);

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

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    public JobPage clickSaveJobButton() {
        logger.info("clicking [SAVE JOB] button");
        getWait().until(ExpectedConditions.elementToBeClickable(saveJobButton));
        saveJobButton.click();
        return newInstance(JobPage.class);
    }

    public NewCustomerPage clickAddNewCustomer() {
        logger.info("clicking [+ NEW CUSTOMER] button");
        getWait().until(ExpectedConditions.visibilityOf(addNewCustomerButton));
        addNewCustomerButton.click();
        return newInstance(NewCustomerPage.class);
    }

    public NewJobPage clickPrivateNotes() {
        logger.info("clicking [Private notes] button");
        getWait().until(ExpectedConditions.visibilityOf(privateNotesButton));
        privateNotesButton.click();
        return this;
    }

    public NewJobPage enterPrivateNotes(String privateNotes) {
        clickPrivateNotes();
        logger.info("entering private notes [{}]", privateNotes);
        getWait().until(ExpectedConditions.visibilityOf(privateNotesTextArea));
        privateNotesTextArea.sendKeys(privateNotes);
        return this;
    }

    @SneakyThrows
    public NewJobPage enterItemName(String itemName) {
        logger.info("entering item name [{}]", itemName);
        Thread.sleep(1000);
//        getWait().until(ExpectedConditions.visibilityOf(itemNameInputField));
        itemNameInputField.sendKeys(itemName);
        return this;
    }

    public NewJobPage enterQuantity(String quantity) {
        logger.info("entering Qty [{}]", quantity);
//        getWait().until(ExpectedConditions.visibilityOf(itemNameInputField));
        quantityInputField.sendKeys(quantity);
        return this;
    }

    public NewJobPage enterQuantity(int quantity) {
        return enterQuantity(String.valueOf(quantity));
    }

    public NewJobPage enterUnitPrice(String unitPrice) {
        logger.info("entering unit price [{}]", unitPrice);

//        getWait().until(ExpectedConditions.visibilityOf(unitPriceInputField));
        unitPriceInputField.sendKeys(BACK_SPACE, BACK_SPACE, BACK_SPACE);

        unitPriceInputField.sendKeys(unitPrice);

        return this;
    }

}
