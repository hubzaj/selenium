package org.housecallpro.test;

import org.housecallpro.utils.PriceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JobTest extends BaseTest {

    // TODO: Readme - asdf, required plugins, allure, env variables
    // TODO: check dependencies version and interference - plugins
    // TODO: Allure - reporting

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
