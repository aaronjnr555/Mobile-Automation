package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.Location;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GeoLocation {
    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        // Appium server URL
        String appiumServerUrl = "http://127.0.0.1:4723";

        // Desired capabilities for Appium session
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "uiautomator2");
        dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        // Create the AndroidDriver instance
        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
    }

    @Test
    public void test() {
        // Interact with the Accessibility button
        Location location = driver.getLocation();
        System.out.println("Current GeoLocation" +location.getLatitude() + location.getLatitude() + location.getAltitude());

        driver.setLocation(new Location(100, 200, 10.0));
    }
    @AfterTest
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
