package base;

import helpers.ScreenShooter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class Listeners implements ITestListener {

  @Override
  public void onTestStart(ITestResult result) {

  }

  @Override
  public void onTestSuccess(ITestResult result) {

  }

  @Override
  public void onTestFailure(ITestResult result) {
    Object currentClass = result.getInstance();
    WebDriver driver = ((BaseTest) currentClass).admin.getDriver();
    Date date = new Date();
    String screenshotName = result.getName().concat("-").concat(String.valueOf(date.getTime()));
    ScreenShooter screenShooter = new ScreenShooter();
    screenShooter.takeScreenshot(driver, screenshotName);
  }

  @Override
  public void onTestSkipped(ITestResult result) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  }

  @Override
  public void onStart(ITestContext context) {

  }

  @Override
  public void onFinish(ITestContext context) {

  }
}