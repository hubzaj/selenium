package org.housecallpro.page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage implements PageInitializer {

    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

}
