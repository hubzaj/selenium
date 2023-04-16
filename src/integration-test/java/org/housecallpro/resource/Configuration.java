package org.housecallpro.resource;

import lombok.Builder.Default;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.housecallpro.browser.BrowserType;

import static org.housecallpro.browser.BrowserType.CHROME;

@Getter
@Slf4j
public class Configuration {

    private static final String BROWSER_ENV = "BROWSER";
    private static final String APPLICATION_URL_ENV = "APPLICATION_URL";

    private static Configuration config;

    @Default
    private BrowserType browser = CHROME;

    @Default
    private String applicationUrl = "https://pro.housecallpro.com/pro/log_in";

    private String os;

    private Configuration() {
        log.info("loading configuration");
        loadBrowserType();
        loadApplicationUrl();
        loadOsName();
    }

    public synchronized static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    private void loadBrowserType() {
        String browserType = System.getenv(BROWSER_ENV);
        browser = browserType != null ? BrowserType.getBrowser(browserType) : browser;
    }

    private void loadApplicationUrl() {
        String url = System.getenv(APPLICATION_URL_ENV);
        applicationUrl = url != null ? url : applicationUrl;
    }

    private void loadOsName() {
        os = System.getProperty("os.name");
    }

}
