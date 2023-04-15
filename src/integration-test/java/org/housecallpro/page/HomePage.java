package org.housecallpro.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "button[data-testid='tracked-button']")
    WebElement newButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickNewButton() {
        logger.info("clicking [New] button");
        getWait().until(ExpectedConditions.visibilityOf(newButton));
        newButton.click();
        return this;
    }

}
