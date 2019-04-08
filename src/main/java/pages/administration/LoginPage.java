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

  private final String pageURL = "https://www.phptravels.net/admin";

  public LoginPage open() {
    Log.debug("START/END LoginPage.open().");
    driver.get(pageURL);
    return this;
  }

  public LoginPage typeEmail(String email) {
    Log.debug("START/END LoginPage.typeEmail(String email) with params {}.", email);
    driver.findElement(EMAIL_INPUT).sendKeys(email);
    return this;
  }

  public LoginPage typePassword(String password) {
    Log.debug("START/END LoginPage.typePassword(String password) with params {}.", password);
    driver.findElement(PASSWORD_INPUT).sendKeys(password);
    return this;
  }

  public void clickSubmit() {
    Log.debug("START/END LoginPage.clickSubmit().");
    driver.findElement(SUBMIT_BUTTON).click();
  }
}
