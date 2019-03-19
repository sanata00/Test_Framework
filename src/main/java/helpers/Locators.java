package helpers;

import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Properties;

public class Locators {

    private static final Properties locators;

    private enum LocatorType {
        ID, NAME, CLASS, TAG, CSS, XPATH, TEXT, PARTTEXT
    }

    static {
        locators = new Properties();
        InputStream inputStream = Locators.class.getResourceAsStream("/locators/locators.properties");
        try {
            locators.load(inputStream);
        } catch (Exception e) {
            //add logging
        }
    }

    public static By get(String locatorName) {
        String propertyValue = locators.getProperty(locatorName);
        return getLocator(propertyValue);
    }

    private static By getLocator(String locator) {
        String[] locatorItems = locator.split("=", 2);
        LocatorType locatorType = LocatorType.valueOf(locatorItems[0]);
        String locatorValue = locatorItems[1];

        switch (locatorType) {
            case ID:
                return By.id(locatorValue);

            case NAME:
                return By.cssSelector(locatorValue);

            case CSS:
                return By.cssSelector(locatorValue);

            case XPATH:
                return By.xpath(locatorValue);

            case TAG:
                return By.cssSelector(locatorValue);

            case CLASS:
                return By.className(locatorValue);

            case TEXT:
                return By.linkText(locatorValue);

            case PARTTEXT:
                return By.partialLinkText(locatorValue);

            default:
                throw new IllegalArgumentException("No such locator type:" + locatorItems[0]);
        }
    }
}
