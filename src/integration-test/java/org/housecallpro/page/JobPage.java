package org.housecallpro.page;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobPage.class);

    @FindBy(xpath = "//span[contains(text(), 'Activity feed')]")
    WebElement activityFeed;
    @FindBy(xpath = "//p[contains(text(), 'Job created as Invoice')]")
    WebElement createdJobInfo;

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public JobPage assertThatJobHasBeenCreated(String totalPrice) {
        scrollToWebElement(activityFeed);
        LOGGER.info("verifying job info entry at [Activity Feed]");
        String expectedRegex = getRegexPatternForConfirmationOfJobCreation(totalPrice);
        Assertions.assertThat(createdJobInfo)
                .isNotNull()
                .extracting(WebElement::getText)
                .isNotNull()
                .matches(actualText -> actualText.matches(expectedRegex), expectedRegex);
        return this;
    }

    private String getRegexPatternForConfirmationOfJobCreation(String totalPrice) {
        totalPrice = totalPrice.contains("\\$") ? totalPrice : totalPrice.replace("$", "\\$");
        return String.format("Job created as Invoice #[0-9]+: total = %s", totalPrice);
    }

}
