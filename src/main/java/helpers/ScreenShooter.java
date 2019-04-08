package helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenShooter {

  private String pathToFolder = "logs\\screens";

  public ScreenShooter() {
    File folderWithScreenshots = new File(pathToFolder);
    Path pathFolderWithScreenshots = folderWithScreenshots.toPath();
    if (Files.notExists(pathFolderWithScreenshots)) {
      if (!folderWithScreenshots.mkdir()) {
        Log.error("The folder {} was not created", pathFolderWithScreenshots);
      }
    }
  }

  public void takeScreenshot(WebDriver driver, String screenshotName) {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File destinationFile = new File(pathToFolder + "\\" + screenshotName + ".png");
    try {
      Files.copy(src.toPath(), destinationFile.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    Log.info("Screenshot {} is taken", destinationFile.getPath());
  }
}