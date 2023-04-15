package org.housecallpro.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.housecallpro.browser.BrowserType;
import org.housecallpro.datastore.User;
import org.housecallpro.page.LoginPage;
import org.housecallpro.page.PageInitializer;
import org.housecallpro.resource.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;

@TestInstance(PER_CLASS)
public abstract class BaseIntegrationTest implements PageInitializer {

    private static final Logger logger = LoggerFactory.getLogger(BaseIntegrationTest.class);
    private static final Configuration config = Configuration.getConfig();

    @Getter
    private WebDriver driver;

    protected LoginPage loginPage;

    @BeforeAll
    void setupBrowser() {
        BrowserType browser = config.getBrowser();
        switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(NORMAL);
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();

                logger.info("[{}] browser has been opened with success", browser.name());
            }
            case CHROME_HEADLESS -> {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(NORMAL);
                options.addArguments("--headless=new");

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();

                logger.info("[{}] browser has been opened with success", browser.name());
            }
            default -> throw new IllegalArgumentException(
                    String.format("Configuration for [%s] browser has not been created", browser));
        }

    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
        logger.info("browser has been closed with success");
    }

    protected LoginPage openApplication() {
        driver.get(config.getApplicationUrl());
        return newInstance(LoginPage.class);
    }

    protected LoginPage loginAs(User user) {
        return openApplication()
                .enterEmail(user.email())
                .enterPassword(user.password())
                .clickSignInButton();
    }

}
