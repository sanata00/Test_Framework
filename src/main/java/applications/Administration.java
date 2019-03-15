package applications;

import base.BaseApplication;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import pages.Administration.DashboardPage;
import pages.Administration.ItemsManagementPage;
import pages.Administration.LoginPage;
import pages.Administration.SidebarMenu;

public class Administration extends BaseApplication {

    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public SidebarMenu sidebarMenu;
    public ItemsManagementPage itemsManagementPage;

    public Administration() {
        loginPage = new LoginPage(super.driver);
        dashboardPage = new DashboardPage(super.driver);
        sidebarMenu = new SidebarMenu(super.driver);
        itemsManagementPage = new ItemsManagementPage(super.driver);
    }

    public void login(String email, String password) {
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

}
