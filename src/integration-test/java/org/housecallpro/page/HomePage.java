package org.housecallpro.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static lombok.AccessLevel.PRIVATE;
import static org.housecallpro.page.HomePage.NewMenuValues.Job;

public class HomePage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "button[data-testid='tracked-button']")
    WebElement newButton;

    @AllArgsConstructor
    public enum NewMenuValues {
        Job(NewJobPage.class);

        @Getter(PRIVATE)
        final Class<? extends BasePage> page;

        private By getLocator() {
            return By.xpath(String.format("//li[contains(text(), '%s')]", this.name()));
        }
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickNewButton() {
        LOGGER.info("clicking [New] button");
        getWait().until(ExpectedConditions.visibilityOf(newButton));
        newButton.click();
        return this;
    }

    public NewJobPage selectJobFromNewMenu() {
        return selectFromNewMenu(Job);
    }

    @SuppressWarnings("unchecked")
    private <T extends BasePage> T selectFromNewMenu(NewMenuValues value) {
        LOGGER.info("selecting [{}] from [New] sub menu", value);
        getDriver().findElement(value.getLocator()).click();
        return (T) newInstance(value.getPage());
    }

}
