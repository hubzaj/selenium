package org.housecallpro.page;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class JobPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Activity feed')]")
    WebElement activityFeed;
    @FindBy(xpath = "//p[contains(text(), 'Job created as Invoice')]")
    WebElement createdJobInfo;

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public JobPage assertThatJobHasBeenCreated(String totalPrice) {
        scrollToWebElement(activityFeed);
        log.info("Assert that a newly created job entry is present at [Activity Feed]");
        String expectedRegex = getRegexPatternForConfirmationOfSuccessfulJobCreation(totalPrice);
        Assertions.assertThat(createdJobInfo)
                .isNotNull()
                .extracting(WebElement::getText)
                .isNotNull()
                .matches(actualText -> actualText.matches(expectedRegex), expectedRegex);
        return this;
    }

    private String getRegexPatternForConfirmationOfSuccessfulJobCreation(String totalPrice) {
        totalPrice = totalPrice.contains("\\$") ? totalPrice : totalPrice.replace("$", "\\$");
        return String.format("Job created as Invoice #[0-9]+: total = %s", totalPrice);
    }

}
