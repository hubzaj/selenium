package org.housecallpro.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.housecallpro.page.PageInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class BaseIntegrationTest implements PageInitializer {

    private static final Logger logger = LoggerFactory.getLogger(BaseIntegrationTest.class);

    WebDriver driver;

    @BeforeAll
    void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("[%s] browser has been opened with success");
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
        logger.info("browser has been closed with success");
    }

    protected void openApplication() {
        driver.get("https://pro.housecallpro.com/pro/log_in");
    }

}
