package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AirplaneModeTest {

    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        // Appium server URL
        String appiumServerUrl = "http://127.0.0.1:4723";

        // Desired capabilities for Appium session
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:appPackage", "com.android.settings");
        dc.setCapability("appium:appActivity", ".Settings");
        dc.setCapability("appium:uiautomator2ServerInstallTimeout", 120000);


        // Create the AndroidDriver instance
        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
    }

    @Test
    public void test2() {
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Network & internet\")")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.settings:id/switchWidget")));
        String airplaneMode = element.getAttribute("checked");
        if(airplaneMode.equals("true")) {
            element.click();
        }
        else {
            System.out.println("Airplane Mode is already turned OFF");
        }
//        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Airplane mode is on\")"))).isDisplayed());
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
