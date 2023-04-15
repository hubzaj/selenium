package org.housecallpro.resource;

import lombok.Builder.Default;
import lombok.Getter;
import org.housecallpro.browser.BrowserType;

import static org.housecallpro.browser.BrowserType.CHROME;

@Getter
public class Configuration {

    private static Configuration config;

    private String os;

    @Default
    private BrowserType browser = CHROME;

    @Default
    private String applicationUrl = "https://pro.housecallpro.com/pro/log_in";

    private Configuration() {
        loadOsName();
        loadBrowserType();
        loadApplicationUrl();
    }

    public synchronized static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    private void loadOsName() {
        os = System.getProperty("os.name");
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
