package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ChangeOrientation {

    public AndroidDriver driver;

    ScreenOrientation currentOrientation;

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
    public void test() {
        currentOrientation = driver.getOrientation();
        System.out.println("Initial Orientation:" + currentOrientation);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        currentOrientation = driver.getOrientation();
        System.out.println("Orientation after rotation:" + currentOrientation);
    }
    @AfterTest
    public void close() {driver.quit();
    }
}
