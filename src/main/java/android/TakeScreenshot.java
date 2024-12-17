package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TakeScreenshot {

    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        // Appium server URL
        String appiumServerUrl = "http://127.0.0.1:4723";

        // Desired capabilities for Appium session
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        // Create the AndroidDriver instance
        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
    }
    @Test
    public void test() throws IOException {

        try {
            WebElement element = driver.findElement(AppiumBy.accessibilityId("Accessibility"));
        }
        catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            captureScreenshot();
        }

    }
    private void captureScreenshot() throws IOException {
        Date today = new Date();
        File screenFile = driver.getScreenshotAs(OutputType.FILE);
        File saveFile = new File("resources/screenshots/screenshot" + "_" + today + ".png");
        Files.copy(screenFile.toPath(), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}

