package org.housecallpro.utils;


import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Instant;


public class ScreenshotExtension implements TestWatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotExtension.class);

    private static final String SCREENSHOT_DIR_PATH = "target/test-classes/screenshots";

    @Setter
    private WebDriver driver;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.info("taking screenshot due to [{}] test failure", context.getDisplayName());
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotFileName = getScreenshotFileName(context);
            FileUtils.copyFile(screenshotFile, new File(SCREENSHOT_DIR_PATH + File.separator + screenshotFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getScreenshotFileName(ExtensionContext context) {
        String testSuiteName = context.getRequiredTestClass()
                .getName()
                .replaceAll("\\.", "_");
        String testMethodName = context.getDisplayName()
                .replace("(", "")
                .replace(")", "");
        long timestamp = Instant.now().getEpochSecond();
        return String.format("%s_%s_%s.png", testSuiteName, testMethodName, timestamp);
    }

}
