package pages.administration;

import base.BasePage;
import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Locators.get;

public class SidebarMenu extends BasePage {

  public SidebarMenu(WebDriver driver) {
    super(driver);
  }

  private final By CARS_MENU = get("admin.menu.cars");
  private final By CARS_CARS_SUBMENU = get("admin.menu.cars.cars");
  private final By CARS_EXTRAS_SUBMENU = get("admin.menu.cars.extras");

  public void expandCarsMenu() {
    Log.debug("START/END SidebarMenu.expandCarsMenu().");
    driver.findElement(CARS_MENU).click();
  }

  public void openSubmenuOfCars(String submenu) {
    Log.debug("START LoginPage.openSubmenuOfCars(String submenu) with params {}.", submenu);
    if (submenu.equals("CARS")) {
      driver.findElement(CARS_CARS_SUBMENU).click();
    } else if (submenu.equals("EXTRAS")) {
      driver.findElement(CARS_EXTRAS_SUBMENU).click();
    } else {
      Log.error("LoginPage.openSubmenuOfCars(String submenu): no such CARS submenu: {}.", submenu);
      throw new IllegalArgumentException("No such CARS submenu:" + submenu);
    }
    Log.debug("END LoginPage.openSubmenuOfCars(String submenu). Submenu is found and clicked.");
  }
}
