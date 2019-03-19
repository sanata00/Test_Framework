package applications;

import base.BaseApplication;
import helpers.Log;
import models.CarExtra;
import org.openqa.selenium.TimeoutException;
import pages.administration.*;

import static org.testng.AssertJUnit.assertEquals;

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
        Log.debug("Logging from application");
        loginPage.open()
                .typeEmail(email)
                .typePassword(password)
                .clickSubmit();
    }

    public void openSubmenuOfCars(String submenu){
        sidebarMenu.expandCarsMenu();
        sidebarMenu.openSubmenuOfCars(submenu);
    }

    public void searchForItem(String item) {
        String initialBenchmark = itemsManagementPage.getBenchmarkTime();
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
            if (!actualBenchmark.equals(initialBenchmark)){
                return;
            }
        }
        throw new TimeoutException("The results were not loaded");
    }

    public void viewItem(CarExtra carExtra) {
        String name = carExtra.getName();
        itemsManagementPage.clickViewButton(name);
    }

    public boolean isCarExtraItemCorrect(CarExtra carExtra) {
        boolean isCorrect = true;
        if (!carExtra.getName().equals(carExtraPage.getName())){
            isCorrect = false;
        }
        if (!carExtra.getStatus().equals(carExtraPage.getStatus())){
            isCorrect = false;
        }
        if (!carExtra.getPrice().equals(carExtraPage.getPrice())){
            isCorrect = false;
        }
        return isCorrect;
    }

}
