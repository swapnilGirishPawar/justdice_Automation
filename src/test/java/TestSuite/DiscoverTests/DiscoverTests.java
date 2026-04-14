package TestSuite.DiscoverTests;

import BaseClass.Hooks;
import Pages.Discover.DiscoverPage;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DiscoverTests extends Hooks {
    private AppiumDriver driver;
    private DiscoverPage discover;

    @BeforeTest(alwaysRun = true)
    public void initDiscoverPage() {
        driver = Hooks.getDriver();
        discover = new DiscoverPage(driver);
    }
}
