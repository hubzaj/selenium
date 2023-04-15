package org.housecallpro.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.InvocationTargetException;

public interface PageInitializer {

    default <T extends BasePage> T newInstance(Class<T> cls) {
        try {
            T page = cls.getConstructor(WebDriver.class).newInstance(getDriver());
            PageFactory.initElements(getDriver(), page);
            return page;
        } catch (NoSuchMethodException
                 | InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException exception) {
            throw new RuntimeException("Error while creating a new page instance", exception);
        }
    }

    WebDriver getDriver();
}
