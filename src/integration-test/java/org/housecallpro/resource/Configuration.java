package org.housecallpro.resource;

public class Configuration {

    private static Configuration config;

    private final BrowserConfig browserConfig;

    private Configuration() {
        browserConfig = BrowserConfig.getConfig();
    }

    public synchronized static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    public static BrowserConfig getBrowserConfig() {
        return getConfig().browserConfig;
    }

}
