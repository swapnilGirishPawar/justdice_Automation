package BaseClass;

import Utils.CommonUtils;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class Hooks extends CommonUtils {

    public Hooks() {
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTestLaunchApp() throws MalformedURLException {
        extentReports("Android", "Cash Giraffe");
        launchApp();
    }

    @BeforeMethod(alwaysRun = true)
    public void createTest(Method method) {
        ExtentTest extentTest = extent.createTest(method.getAnnotation(Test.class).description());
        setTest(extentTest);
    }

    @AfterMethod(alwaysRun = true)
    public void clearExtentTest() {
        removeTest();
    }

    @AfterTest(alwaysRun = true)
    public void quitDriver() {
        tearDown();
    }

    @AfterSuite(alwaysRun = true)
    public void after() {
        extent.flush();
    }
}