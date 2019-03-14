package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminTests extends BaseTest {

    @Test
    public void canLogin() {
        admin.login("admin@phptravels.com", "demoadmin");
        assertTrue(admin.dashboardPage.isPageOpened());
    }

}
