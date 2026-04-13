package BaseClass;

import Utils.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;



public class Hooks extends CommonUtils {

    public Hooks() throws Throwable {
    }


    @BeforeTest(alwaysRun = true)
    public void beforeTestLaunchApp() throws Throwable {
        launchApp();
        extentReports("Android", "Cash Giraffe");
    }

    @AfterSuite(alwaysRun = true)
    public void after() throws Throwable {
        extent.flush();
    }


    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();



    public static void launchApp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setUdid("emulator-5554");
        options.setAppPackage("cashgiraffe.app");
        options.setAppActivity("de.mcoins.applike.LauncherDefault");

        options.setNoReset(true);
        options.setFullReset(false);

        driver.set(new AndroidDriver(
                new URL("http://127.0.0.1:4723/"),
                options
        ));

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}