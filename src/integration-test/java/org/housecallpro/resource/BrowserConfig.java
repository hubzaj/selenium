package org.housecallpro.resource;

import lombok.Builder.Default;
import lombok.Getter;
import org.housecallpro.browser.BrowserType;

import static org.housecallpro.browser.BrowserType.CHROME;

@Getter
public class BrowserConfig {

    private static BrowserConfig config;

    @Default
    private BrowserType browser = CHROME;


    private BrowserConfig() {
        browser = System.getenv("BROWSER") != null
                ? BrowserType.getBrowser(System.getenv("BROWSER"))
                : browser;
    }

    public synchronized static BrowserConfig getConfig() {
        if (config == null) {
            config = new BrowserConfig();
        }
        return config;
    }

}
