package pages.administration;

import base.BasePage;
import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.Locators.get;

public class ItemsManagementPage extends BasePage {

  public ItemsManagementPage(WebDriver driver) {
    super(driver);
  }

  private final int CAR_NAME_COLUMN = 5;
  private final int CAR_EXTRA_NAME_COLUMN = 4;

  private final By SEARCH_BUTTON = get("admin.management.searchButton");
  private final By SEARCH_INPUT = get("admin.management.searchInput");
  private final By GO_BUTTON = get("admin.management.go");
  private final By ITEMS = get("admin.management.items"); //array of TRs
  private final By BENCHMARK_TIME = get("admin.management.benchmarkTime");

  public boolean isInNameColumn(String value) {
    Log.debug("START ItemsManagementPage.isInNameColumn(String value) with params {}.", value);
    List<WebElement> lines = driver.findElements(ITEMS);
    for (WebElement line : lines) {
      String name = line.findElement(By.cssSelector("td:nth-child(" + CAR_NAME_COLUMN + ")")).getText();
      Log.debug("Extracted name: {}.", name);
      if (name.equals(value)) {
        Log.debug("END ItemsManagementPage.isInNameColumn(String value): The name is found.");
        return true;
      }
    }
    Log.debug("END ItemsManagementPage.isInNameColumn(String value): The name is NOT found.");
    return false;
  }

  public ItemsManagementPage clickSearch() {
    Log.debug("START ItemsManagementPage.clickSearch().");
    driver.findElement(SEARCH_BUTTON).click();
    Log.debug("END ItemsManagementPage.clickSearch().");
    return this;
  }

  public ItemsManagementPage clickGo() {
    Log.debug("START ItemsManagementPage.clickGo().");
    //Sometimes cannot be click immediately after type a search value
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.findElement(GO_BUTTON).click();
    Log.debug("END ItemsManagementPage.clickGo().");
    return this;
  }

  public ItemsManagementPage typeSearchValue(String value) {
    Log.debug("START ItemsManagementPage.typeSearchValue(String value) with params {}.", value);
    driver.findElement(SEARCH_INPUT).sendKeys(value);
    Log.debug("END ItemsManagementPage.typeSearchValue(String value).");
    return this;
  }

  public String getBenchmarkTime() {
    Log.debug("START ItemsManagementPage.getBenchmarkTime().");
    String benchmarkTime = driver.findElement(BENCHMARK_TIME).getText();
    Log.debug("END ItemsManagementPage.getBenchmarkTime(). Benchmark time: {}.", benchmarkTime);
    return benchmarkTime;
  }

  public void clickViewButton(String name) {
    Log.debug("START ItemsManagementPage.clickViewButton(String name) with params {}.", name);
    boolean isClicked = false;
    List<WebElement> lines = driver.findElements(ITEMS);
    for (WebElement line : lines) {
      String currentName = line.findElement(By.cssSelector("td:nth-child(" + CAR_EXTRA_NAME_COLUMN + ")")).getText();
      Log.debug("Current name: {}.", currentName);
      if (currentName.equals(name)) {
        line.findElement(By.cssSelector("a[title=View]")).click();
        isClicked = true;
      }
    }
    if (!isClicked) {
      Log.error("ItemsManagementPage.clickViewButton(String name): Couldn't find the name {}.", name);
      throw new IllegalArgumentException("Couldn't find the name '" + name + "'.");
    }
    Log.debug("END ItemsManagementPage.clickViewButton(String name). The button is clicked.");
  }
}
