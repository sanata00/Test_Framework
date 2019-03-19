package pages.administration;

import base.BasePage;
import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Locators.get;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By EMAIL_INPUT = get("admin.login.email");
    private final By PASSWORD_INPUT = get("admin.login.password");
    private final By SUBMIT_BUTTON = get("admin.login.submit");

    public LoginPage open() {
        Log.debug("opening page");
        driver.get("https://www.phptravels.net/admin");
        return this;
    }

    public LoginPage typeEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public void clickSubmit() {
        driver.findElement(SUBMIT_BUTTON).click();
    }
}
