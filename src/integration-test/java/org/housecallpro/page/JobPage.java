package org.housecallpro.page;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Activity feed')]")
    WebElement activityFeed;

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public JobPage assertThatJobHasBeenCreated(String total) {
        scrollToWebElement(activityFeed);
        WebElement actualWebElement = activityFeed.findElement(
                By.xpath("//p[contains(text(), 'Job created as Invoice')]"));
        String expectedRegex = String.format("Job created as Invoice #[0-9]+: total = \\$%s", total);
        Assertions.assertThat(actualWebElement)
                .isNotNull()
                .extracting(WebElement::getText)
                .isNotNull()
                .matches(actualText -> actualText.matches(expectedRegex), expectedRegex);
        return this;
    }

}
