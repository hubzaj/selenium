package org.housecallpro.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(JobPage.class);

    @FindBy(xpath = "//span[contains(text(), '+ New customer')]")
    WebElement addNewCustomerButton;


    public JobPage(WebDriver driver) {
        super(driver);
    }

    public NewCustomerPage clickAddNewCustomer() {
        logger.info("clicking [+ NEW CUSTOMER] button");
        getWait().until(ExpectedConditions.visibilityOf(addNewCustomerButton));
        addNewCustomerButton.click();
        return newInstance(NewCustomerPage.class);
    }

}
