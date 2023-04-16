package org.housecallpro.test;

import lombok.Getter;
import org.housecallpro.browser.BrowserFactory;
import org.housecallpro.datastore.User;
import org.housecallpro.page.HomePage;
import org.housecallpro.page.LoginPage;
import org.housecallpro.page.PageInitializer;
import org.housecallpro.resource.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class BaseTest implements PageInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private static final Configuration config = Configuration.getConfig();

    @Getter
    private WebDriver driver;

    protected HomePage homePage;

    @BeforeAll
    void beforeAll() {
        driver = BrowserFactory.createBrowser(config.getBrowser());
    }

    @AfterEach
    void afterAll() {
        driver.quit();
        LOGGER.info("browser has been closed with success");
    }

    protected LoginPage openApplication() {
        driver.get(config.getApplicationUrl());
        return newInstance(LoginPage.class);
    }

    protected HomePage loginAs(User user) {
        return openApplication()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickSignInButton();
    }

}
