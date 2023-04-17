package org.housecallpro.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.housecallpro.utils.PriceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("[JIRA-1]: POC of test framework and happy path")
public class JobTest extends BaseTest {

    @BeforeAll
    void beforeAll() {
        homePage = loginWithDefaultUser();
    }

    @Story("[JIRA-2] Create happy path test")
    @DisplayName("should create a new job")
    @Severity(CRITICAL)
    @Test
    void shouldCreateNewJob() {
        // Given
        int quantity = 2;
        int unitPrice = 200;
        String expectedTotalPrice = PriceUtils.calculateTotalPrice(quantity, unitPrice);

        // When
        homePage.clickNewButton()
                .selectJobFromNewMenu()
                .clickAddNewCustomer()
                .enterFirstName("Mark")
                .clickCreateCustomerButton()
                .enterItemName("test item")
                .enterQuantity(quantity)
                .enterUnitPrice(unitPrice)
                .enterPrivateNotes("test private notes")
                .clickSaveJobButton()

                // Then
                .assertThatJobHasBeenCreated(expectedTotalPrice);
    }

}
