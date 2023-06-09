package org.housecallpro.page;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.housecallpro.resource.Configuration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.Platform.WINDOWS;

@Slf4j
public abstract class BasePage implements PageInitializer {

    private final WebDriver driver;
    @Getter
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    protected void scrollToWebElement(WebElement webElement) {
        log.info("Scrolling to web element");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    protected String getSelectAllTextKeys() {
        if (Configuration.getConfig().getOs().equals(WINDOWS.name())) {
            return Keys.CONTROL + "a";
        }
        return Keys.COMMAND + "a";
    }

}
