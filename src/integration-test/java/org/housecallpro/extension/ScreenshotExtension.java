package org.housecallpro.extension;


import io.qameta.allure.Attachment;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

@Slf4j
public class ScreenshotExtension implements TestWatcher {

    private static final String SCREENSHOT_DIR_PATH = "target/test-classes/screenshots";

    @Setter
    private WebDriver driver;

    @Attachment(value = "Screenshot from web page.", type = "image/png")
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("Taking screenshot due to [{}] test failure", context.getDisplayName());
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
