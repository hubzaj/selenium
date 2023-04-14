package org.housecallpro.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.housecallpro.browser.BrowserType;
import org.housecallpro.page.PageInitializer;
import org.housecallpro.resource.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class BaseIntegrationTest implements PageInitializer {

    private static final Logger logger = LoggerFactory.getLogger(BaseIntegrationTest.class);

    WebDriver driver;

    @BeforeAll
    void setupBrowser() {
        BrowserType browser = Configuration.getBrowserConfig().getBrowser();
        switch (browser) {
            case CHROME_DOCKER -> {
                if (WebDriverManager.isDockerAvailable()) {
                    WebDriverManager.chromedriver().browserInDocker().setup();
                    driver = new ChromeDriver();
                    logger.info("[{}] browser has been opened with success", browser.name());
                }
            }
            case SAFARI -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                logger.info("[{}] browser has been opened with success", browser.name());
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("[{}] browser has been opened with success", browser.name());
            }
        }

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
