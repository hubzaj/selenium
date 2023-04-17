package org.housecallpro.test;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.housecallpro.browser.BrowserFactory;
import org.housecallpro.datastore.User;
import org.housecallpro.datastore.UsersManager;
import org.housecallpro.extension.ScreenshotExtension;
import org.housecallpro.page.HomePage;
import org.housecallpro.page.LoginPage;
import org.housecallpro.page.PageInitializer;
import org.housecallpro.resource.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@Slf4j
public abstract class BaseTest implements PageInitializer {

    private static final Configuration CONFIG = Configuration.getConfig();
    private static final UsersManager USER_MANAGER = UsersManager.getUserManager();

    @RegisterExtension
    ScreenshotExtension screenshotExtension = new ScreenshotExtension();

    @Getter
    private WebDriver driver;

    protected HomePage homePage;
    private User user;

    @BeforeAll
    void setup() {
        driver = BrowserFactory.createBrowser(CONFIG.getBrowser());
        screenshotExtension.setDriver(driver);

        user = USER_MANAGER.reserveUser();
    }

    @AfterAll
    void teardown() {
        driver.close();
        driver.quit();
        log.info("Browser has been closed with success");
        USER_MANAGER.releaseUser(user);
    }

    @Step("Open web browser and load login page")
    protected LoginPage openApplication() {
        driver.get(CONFIG.getApplicationUrl());
        return newInstance(LoginPage.class);
    }

    @Step("Login to application as {user.email}")
    protected HomePage loginAs(User user) {
        return openApplication()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickSignInButton();
    }

    protected HomePage loginWithDefaultUser() {
        return loginAs(user);
    }

}
