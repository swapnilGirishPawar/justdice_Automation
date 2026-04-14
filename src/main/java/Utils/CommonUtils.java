package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    public static String reportFolderPath = "reports";
    public static ExtentReports extent = new ExtentReports();
    public static ExtentSparkReporter spark;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();


    public static void extentReports(String platform, String appName) {
//        spark.config().setTimelineEnabled(true);
        spark = new ExtentSparkReporter(reportFolderPath + "/" + platform + "_" + appName + "_" + getCurrentDateTime() + ".html");
        spark.config().setCss(
                ".header { animation: zoomIn 0.5s; }" +
                        ".test.pass { animation: fadeIn 0.5s ease-in-out; }" +
                        ".test.fail { animation: shake 0.5s; }" +
                        ".test-summary { animation: pulse 1.5s infinite; }" +
                        ".test:hover { background-color: #f0f8ff; transform: scale(1.02); transition: 0.3s ease-in-out; }" +
                        "@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }" +
                        "@keyframes shake { 0%, 100% { transform: translateX(0); } 25% { transform: translateX(-5px); } 50% { transform: translateX(5px); } 75% { transform: translateX(-5px); } }" +
                        "@keyframes zoomIn { from { transform: scale(0.5); } to { transform: scale(1); } }" +
                        "@keyframes pulse { 0% { transform: scale(1); } 50% { transform: scale(1.05); } 100% { transform: scale(1); } }"
        );
        spark.config().setDocumentTitle(appName + "Automation Report");
        spark.config().setReportName(platform + appName);
        extent.attachReporter(spark);
    }
    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a custom date and time format if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
        // Format the current date and time using the formatter
        return currentDateTime.format(formatter);
    }

    public void passMessage(String msg){
        System.out.println(msg);
        getTest().pass(msg);
    }
    public void infoMessage(String msg){
        System.out.println(msg);
        getTest().info(msg);
    }
    public void failMessage(Throwable e) {
        getTest().fail("Test failed: " + e.getMessage());
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
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
        System.out.println("App Launch Started");
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


    public static AppiumDriver getDriver() {
        return driver.get();
    }


    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest testInstance) {
        extentTest.set(testInstance);
    }

    public static void removeTest() {
        extentTest.remove();
    }

    public static void tearDown() {
        System.out.println("Tear Down Started");
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

}
