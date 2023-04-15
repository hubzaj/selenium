package org.housecallpro.resource;

import lombok.Builder.Default;
import lombok.Getter;
import org.housecallpro.browser.BrowserType;

import static org.housecallpro.browser.BrowserType.CHROME;

@Getter
public class Configuration {

    private static Configuration config;

    @Default
    private BrowserType browser = CHROME;

    @Default
    private String applicationUrl = "https://pro.housecallpro.com/pro/log_in";

    private Configuration() {
        loadBrowserType();
        loadApplicationUrl();
    }

    public synchronized static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    private void loadBrowserType() {
        String browserType = System.getenv("BROWSER");
        browser = browserType != null ? BrowserType.getBrowser(browserType) : browser;
    }

    private void loadApplicationUrl() {
        String url = System.getenv("APPLICATION_URL");
        applicationUrl = url != null ? url : applicationUrl;
    }

}
