package org.housecallpro.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.housecallpro.page.PageInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class BaseIntegrationTest implements PageInitializer {

    WebDriver driver;

    @BeforeAll
    void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

    protected void openApplication() {
        driver.get("https://pro.housecallpro.com/pro/log_in");
    }

}
