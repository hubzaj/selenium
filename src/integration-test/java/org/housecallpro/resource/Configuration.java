package org.housecallpro.resource;

public class Configuration {

    private static Configuration config;

    private Configuration() {
    }

    public synchronized static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

}
