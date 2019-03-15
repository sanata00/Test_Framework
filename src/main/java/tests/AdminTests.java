package tests;

import base.BaseTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminTests extends BaseTest {

    @BeforeSuite
    public void canLogin() {
        admin.login("admin@phptravels.com", "demoadmin");
    }

    @Test
    public void searchForCar() {
        admin.openSubmenuOfCars("CARS");
        admin.searchForItem("Ford Mondeo 2012");
        assertTrue(admin.itemsManagementPage.isInNameColumn("Ford Mondeo 2012"));

    }

}
