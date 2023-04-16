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

                log.info("[{}] browser has been opened with success", browser.name());
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

                log.info("[{}] browser has been opened with success", browser.name());
                yield driver;
            }
        };
    }

}
