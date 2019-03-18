package pages.Administration;

import base.BasePage;
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
        List<WebElement> lines = driver.findElements(ITEMS);
        for (WebElement line : lines) {
            String name = line.findElement(By.cssSelector("td:nth-child(" + CAR_NAME_COLUMN + ")")).getText();
            if (name.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public ItemsManagementPage clickSearch() {
        driver.findElement(SEARCH_BUTTON).click();
        return this;
    }

    public ItemsManagementPage clickGo() {
        //Sometimes cannot be click immediately after type a search value
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(GO_BUTTON).click();
        return this;
    }

    public ItemsManagementPage typeSearchValue(String value) {
        driver.findElement(SEARCH_INPUT).sendKeys(value);
        return this;
    }

    public String getBenchmarkTime() {
        return driver.findElement(BENCHMARK_TIME).getText();
    }

    public void clickViewButton(String name) {
        boolean isClicked = false;
        List<WebElement> lines = driver.findElements(ITEMS);
        for (WebElement line : lines) {
            String currentName = line.findElement(By.cssSelector("td:nth-child(" + CAR_EXTRA_NAME_COLUMN + ")")).getText();
            if (currentName.equals(name)) {
                line.findElement(By.cssSelector("a[title=View]")).click();
                isClicked = true;
            }
        }
        if (!isClicked) {
            throw new IllegalArgumentException("Couldn't find a name '" + name + "'.");
        }
    }
}
