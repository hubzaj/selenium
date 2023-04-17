package org.housecallpro.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.openqa.selenium.PageLoadStrategy.NORMAL;

@Slf4j
public class BrowserFactory {

    private static final String OPEN_BROWSER_WITH_SUCCESS_LOG_MESSAGE = "[{}] browser has been opened with success";
    private static final String HEADLESS_MODE = "--headless=new";

    private BrowserFactory() {
    }

    public static WebDriver createBrowser(BrowserType browser) {
        return switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                WebDriver driver = createDefaultChromeDriver();
                log.info(OPEN_BROWSER_WITH_SUCCESS_LOG_MESSAGE, browser.name());
                yield driver;
            }
            case CHROME_HEADLESS -> {
                WebDriverManager.chromedriver().setup();
                WebDriver driver = createDefaultChromeDriver(HEADLESS_MODE);
                log.info(OPEN_BROWSER_WITH_SUCCESS_LOG_MESSAGE, browser.name());
                yield driver;
            }
            case CHROME_IN_DOCKER -> {
                WebDriverManager.chromedriver().browserInDocker().create();
                WebDriver driver = createDefaultChromeDriver(HEADLESS_MODE);
                log.info(OPEN_BROWSER_WITH_SUCCESS_LOG_MESSAGE, browser.name());
                yield driver;
            }
        };
    }

    private static WebDriver createDefaultChromeDriver(String... arguments) {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(NORMAL);
        options.addArguments(arguments);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        return driver;
    }

}
