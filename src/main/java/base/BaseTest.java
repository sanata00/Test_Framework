package base;

import applications.Administration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected Administration admin;

    @BeforeSuite
    public void start() {
        admin = new Administration();
    }

    @AfterSuite
    public void finish() {
        admin.quit();
    }
}
