package pages.administration;

import base.BasePage;
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
        return driver.findElement(NAME).getText();
    }

    public String getStatus() {
        return driver.findElement(STATUS).getText();
    }

    public String getPrice() {
        return driver.findElement(PRICE).getText();
    }
}
