package org.housecallpro.browser;

import java.util.Arrays;

public enum BrowserType {
    CHROME,
    CHROME_HEADLESS,
    CHROME_DOCKER,
    SAFARI;

    public static BrowserType getBrowser(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    String.format("[%s] browser is not supported. Please use one of [%s]",
                            name,
                            Arrays.stream(values()).map(Enum::toString).toList())
            );
        }
    }

}
