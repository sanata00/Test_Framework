package tests;

import base.BaseTest;
import models.CarExtra;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class AdminTests extends BaseTest {

    @Parameters({"email", "password"})
    @BeforeSuite(alwaysRun = true)
    public void canLogin(String email, String password) {
        admin.login(email, password);
        assertTrue(admin.dashboardPage.isPageOpened());
    }

    @Test(dataProvider = "xmlProvider", dataProviderClass = AdminDataProviders.class)
    public void searchForCar(String carName) {
        admin.openSubmenuOfCars("CARS");
        admin.searchForItem(carName);
        assertTrue(admin.itemsManagementPage.isInNameColumn(carName));
    }

    @Test(dataProvider = "carExtraProvider", dataProviderClass = AdminDataProviders.class, groups = {"group1"})
    public void isCarExtraItemCorrect(CarExtra carExtra, Map<String, String> map) {
        System.out.println("Just for demonstration: " + map.get("someTestData"));
        admin.openSubmenuOfCars("EXTRAS");
        admin.viewItem(carExtra);
        assertTrue(admin.isCarExtraItemCorrect(carExtra));
    }



}
