package pages.Administration;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Locators.get;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public final By EMAIL_INPUT = get("admin.login.email");
    public final By PASSWORD_INPUT = get("admin.login.password");
    public final By SUBMIT_BUTTON = get("admin.login.submit");

    public LoginPage open() {
        driver.get("https://www.phptravels.net/admin");
        return this;
    }

}
