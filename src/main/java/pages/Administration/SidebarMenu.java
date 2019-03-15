package pages.Administration;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Locators.get;

public class SidebarMenu extends BasePage {

    public SidebarMenu(WebDriver driver) {
        super(driver);
    }

    private final By CARS_MENU = get("admin.menu.cars");
    private final By CARS_CARS_SUBMENU = get("admin.menu.cars.cars");

    public void expandCarsMenu() {
        driver.findElement(CARS_MENU).click();
    }

    public void openSubmenuOfCars(String submenu) {
        if (submenu.equals("CARS")) {
            driver.findElement(CARS_CARS_SUBMENU).click();
        } else {
            throw new IllegalArgumentException("No such CARS submenu:" + submenu);
        }
    }
}
