package base;

import applications.Administration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected Administration admin;

    @BeforeSuite (alwaysRun = true)
    public void start() {
        admin = new Administration();
    }

    @AfterSuite (alwaysRun = true)
    public void finish() {
        admin.quit();
    }
}
