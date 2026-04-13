package BaseClass;

import Utils.CommonUtils;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class Hooks extends CommonUtils {
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public Hooks() {
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTestLaunchApp() throws MalformedURLException {
        extentReports("Android", "Cash Giraffe");
        launchApp();
    }

    @BeforeMethod(alwaysRun = true)
    public void createTest(Method method) {
        ExtentTest extentTest = extent.createTest(method.getName());
        setTest(extentTest);
    }

    @AfterTest(alwaysRun = true)
    public void quitDriver() {
        tearDown();
    }

    @AfterSuite(alwaysRun = true)
    public void after() {
        extent.flush();
    }

    public static void launchApp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setUdid("emulator-5554");
        options.setAppPackage("cashgiraffe.app");
//        options.setAppActivity("de.mcoins.applike.LauncherDefault");
        options.setAppWaitActivity("*");
        options.setAutoGrantPermissions(true); // notification prompt - off

        options.setNoReset(false);

        driver.set(new AndroidDriver(
                new URL("http://127.0.0.1:4723/"),
                options
        ));

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


    public static AppiumDriver getDriver() {
        return driver.get();
    }


    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}