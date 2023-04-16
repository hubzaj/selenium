package org.housecallpro.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.openqa.selenium.PageLoadStrategy.NORMAL;

public class BrowserFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserFactory.class);

    private BrowserFactory() {
    }

    public static WebDriver createBrowser(BrowserType browser) {
        return switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(NORMAL);
                options.addArguments("--remote-allow-origins=*");

                WebDriver driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                driver.manage().window().maximize();

                LOGGER.info("[{}] browser has been opened with success", browser.name());
                yield driver;
            }
            case CHROME_HEADLESS -> {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(NORMAL);
                options.addArguments("--headless=new");

                WebDriver driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                driver.manage().window().maximize();

                LOGGER.info("[{}] browser has been opened with success", browser.name());
                yield driver;
            }
        };
    }

}
