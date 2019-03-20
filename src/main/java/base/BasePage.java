package base;

import helpers.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isElementPresent(By locator) {
        Log.debug("START BasePage.isElementPresent(By locator) with params {}.", locator.toString());
        try {
            driver.findElement(locator);
            Log.debug("END BasePage.isElementPresent(By locator). The element is found.");
            return true;
        } catch (NoSuchElementException ex) {
            Log.debug("END BasePage.isElementPresent(By locator). The element is NOT found.");
            return false;
        }
    }
}
