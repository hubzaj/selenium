package org.housecallpro.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.housecallpro.datastore.User;
import org.housecallpro.resource.TestUsersFetcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExampleTest extends BaseTest {

    // TODO: test users manager
    // TODO: finish logger configuration and be able to see logs
    // TODO: Test phases implementation and override with loggers
    // TODO: Screenshot in case of failure
    // TODO: Allure - reporting
    // TODO: check dependencies version and interference - plugins
    // TODO: Readme - asdf, required plugins, allure, env variables

    @BeforeAll
    void beforeAll() {
        List<User> testUsers = TestUsersFetcher.getTestUsers();
        User user = testUsers.get(0);

        homePage = loginAs(user);
    }

    @Test
    void shouldCreateNewJob() {
        homePage.clickNewButton()
                .selectJobFromNewMenu()
                .clickAddNewCustomer()
                .enterFirstName("Mark")
                .clickCustomerCustomerButton()
                .enterItemName("asdsadsa")
                .enterUnitPrice("200")
                .enterPrivateNotes(RandomStringUtils.randomAlphabetic(10))
                .clickSaveJobButton();
    }

}
