package org.housecallpro.test;

import org.apache.commons.lang3.RandomStringUtils;
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
        // TODO: helper method to calculate expected total price
//        int expectedTotalPrice = quantity * unitPrice;
        String expectedTotalPrice = "400.00";

        // When
        homePage.clickNewButton()
                .selectJobFromNewMenu()
                .clickAddNewCustomer()
                .enterFirstName("Mark")
                .clickCreateCustomerButton()
                .enterItemName("asdsadsa")
                .enterQuantity(quantity)
                .enterUnitPrice("200")
                .enterPrivateNotes(RandomStringUtils.randomAlphabetic(10))
                .clickSaveJobButton()

                // Then
                .assertThatJobHasBeenCreated(expectedTotalPrice);
    }

}
