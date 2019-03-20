package tests;

import base.BaseTest;
import helpers.Log;
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
        Log.info("START AdminTests.canLogin.");
        admin.login(email, password);
        assertTrue(admin.dashboardPage.isPageOpened());
        Log.info("END AdminTests.canLogin.");
    }

    @Test(dataProvider = "xmlProvider", dataProviderClass = AdminDataProviders.class, groups = {"regression"})
    public void searchForCar(String carName) {
        Log.info("START AdminTests.searchForCar.");
        admin.openSubmenuOfCars("CARS");
        admin.searchForItem(carName);
        assertTrue(admin.itemsManagementPage.isInNameColumn(carName));
        Log.info("END AdminTests.searchForCar.");
    }

    @Test(dataProvider = "carExtraProvider", dataProviderClass = AdminDataProviders.class, groups = {"regression"})
    public void isCarExtraItemCorrect(CarExtra carExtra, Map<String, String> map) {
        Log.info("START AdminTests.isCarExtraItemCorrect.");
        admin.openSubmenuOfCars("EXTRAS");
        admin.viewItem(carExtra);
        assertTrue(admin.isCarExtraItemCorrect(carExtra));
        Log.info("END AdminTests.isCarExtraItemCorrect.");
    }



}
