package applications;

import base.BaseApplication;
import helpers.Log;
import models.CarExtra;
import org.openqa.selenium.TimeoutException;
import pages.administration.*;

public class Administration extends BaseApplication {

    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public SidebarMenu sidebarMenu;
    public ItemsManagementPage itemsManagementPage;
    public CarExtraPage carExtraPage;

    public Administration() {
        loginPage = new LoginPage(super.driver);
        dashboardPage = new DashboardPage(super.driver);
        sidebarMenu = new SidebarMenu(super.driver);
        itemsManagementPage = new ItemsManagementPage(super.driver);
        carExtraPage = new CarExtraPage(super.driver);
    }

    public void login(String email, String password) {
        Log.debug("START Administration.login(String email, String password) with params {}, {}."
                , email, password);
        loginPage.open()
                .typeEmail(email)
                .typePassword(password)
                .clickSubmit();
        Log.debug("END Administration.login(String email, String password)");
    }

    public void openSubmenuOfCars(String submenu){
        Log.debug("START Administration.openSubmenuOfCars(String submenu) with params {}.", submenu);
        sidebarMenu.expandCarsMenu();
        sidebarMenu.openSubmenuOfCars(submenu);
        Log.debug("END Administration.openSubmenuOfCars(String submenu)");
    }

    public void searchForItem(String item) {
        Log.debug("START Administration.searchForItem(String item) with params {}.", item);
        String initialBenchmark = itemsManagementPage.getBenchmarkTime();
        Log.debug("Initial benchmark: {}.", initialBenchmark);
        itemsManagementPage.clickSearch()
                .typeSearchValue(item)
                .clickGo();

        //Cause we can't identify WebElement of a loading circle,
        //we assume that after loading time benchmark is updated
        String actualBenchmark;
        for (int i=0; i<10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualBenchmark = itemsManagementPage.getBenchmarkTime();
            Log.debug("Actual benchmark: {}.", actualBenchmark);
            if (!actualBenchmark.equals(initialBenchmark)){
                Log.debug("END Administration.searchForItem(String item). The item is found.");
                return;
            }
        }
        Log.error("Administration.searchForItem(String item): The results were not loaded.");
        throw new TimeoutException("The results were not loaded");

    }

    public void viewItem(CarExtra carExtra) {
        Log.debug("START Administration.viewItem(CarExtra carExtra) with params {object}.");
        String name = carExtra.getName();
        itemsManagementPage.clickViewButton(name);
        Log.debug("END Administration.viewItem(CarExtra carExtra)");
    }

    public boolean isCarExtraItemCorrect(CarExtra carExtra) {
        Log.debug("START Administration.isCarExtraItemCorrect(CarExtra carExtra) with params {object}.");
        boolean isCorrect = true;

        String expectedName = carExtra.getName();
        String actualName = carExtraPage.getName();
        if (!expectedName.equals(actualName)){
            Log.debug("NAMEs are not equal (expected, actual): {}, {}.", expectedName, actualName);
            isCorrect = false;
        }

        String expectedStatus = carExtra.getStatus();
        String actualStatus = carExtraPage.getStatus();
        if (!expectedStatus.equals(actualStatus)){
            Log.debug("STATUSes are not equal (expected, actual): {}, {}.", expectedStatus, actualStatus);
            isCorrect = false;
        }

        String expectedPrice = carExtra.getPrice();
        String actualPrice = carExtraPage.getPrice();
        if (!expectedPrice.equals(actualPrice)){
            Log.debug("PRICEs are not equal (expected, actual): {}, {}.", expectedPrice, actualPrice);
            isCorrect = false;
        }
        Log.debug("END Administration.isCarExtraItemCorrect(CarExtra carExtra).");
        return isCorrect;
    }

}
