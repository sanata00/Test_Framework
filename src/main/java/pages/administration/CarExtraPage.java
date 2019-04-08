package pages.administration;

import base.BasePage;
import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Locators.get;

public class CarExtraPage extends BasePage {
  public CarExtraPage(WebDriver driver) {
    super(driver);
  }

  private final By NAME = get("admin.carExtra.name");
  private final By STATUS = get("admin.carExtra.status");
  private final By PRICE = get("admin.carExtra.price");

  public String getName() {
    Log.debug("START CarExtraPage.getName().");
    String name = driver.findElement(NAME).getText();
    Log.debug("END CarExtraPage.getName(). Name: {}.", name);
    return name;
  }

  public String getStatus() {
    Log.debug("START CarExtraPage.getStatus().");
    String status = driver.findElement(STATUS).getText();
    Log.debug("END CarExtraPage.getStatus(). Status: {}.", status);
    return status;
  }

  public String getPrice() {
    Log.debug("START CarExtraPage.getPrice().");
    String price = driver.findElement(PRICE).getText();
    Log.debug("END CarExtraPage.getPrice(). Price: {}.", price);
    return price;
  }
}
