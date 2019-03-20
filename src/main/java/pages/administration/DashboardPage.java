package pages.administration;

import base.BasePage;
import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Locators.get;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private final By DIV_MAIN = get("admin.dash.main");

    private final String url = "https://www.phptravels.net/admin";
    private final String title = "Dashboard";

    public boolean isPageOpened() {
        Log.debug("START/END DashboardPage.isPageOpened().");
        return isElementPresent(DIV_MAIN);
    }
}
