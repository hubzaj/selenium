package org.housecallpro.test;

import org.housecallpro.datastore.User;
import org.housecallpro.resource.TestUsersFetcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExampleIntegrationTest extends BaseIntegrationTest {

    @BeforeAll
    void beforeAll() {
        List<User> testUsers = TestUsersFetcher.getTestUsers();
        User user = testUsers.get(0);

        loginPage = loginAs(user);
    }

    @Test
    void shouldOpenApplication() {

    }

}
