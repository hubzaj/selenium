package org.housecallpro.test;

import org.housecallpro.utils.PriceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ExampleTest extends BaseTest {

    // TODO: finish logger configuration and be able to see logs
    // TODO: Test phases implementation and override with loggers
    // TODO: Screenshot in case of failure
    // TODO: Allure - reporting
    // TODO: check dependencies version and interference - plugins
    // TODO: Readme - asdf, required plugins, allure, env variables

    @BeforeAll
    void beforeAll() {
        homePage = loginWithDefaultUser();
    }

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
