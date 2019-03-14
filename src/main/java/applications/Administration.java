package applications;

import base.BaseApplication;
import org.openqa.selenium.WebDriver;
import pages.Administration.DashboardPage;
import pages.Administration.LoginPage;

public class Administration extends BaseApplication {

    public LoginPage loginPage;
    public DashboardPage dashboardPage;

    public Administration() {
        loginPage = new LoginPage(super.driver);
        dashboardPage = new DashboardPage(super.driver);
    }

    public void login(String email, String password) {
        loginPage.open();
        loginPage.sendKeys(loginPage.EMAIL_INPUT, email);
        loginPage.sendKeys(loginPage.PASSWORD_INPUT, password);
        loginPage.click(loginPage.SUBMIT_BUTTON);
    }

}
