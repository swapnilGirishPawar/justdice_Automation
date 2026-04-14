package TestSuite.LoginTests;

import BaseClass.Hooks;
import Pages.Discover.DiscoverPage;
import Pages.Login.LoginPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginTests extends Hooks {
    private AppiumDriver driver;
    private LoginPage loginPage;
    private DiscoverPage discoverPage;

    @BeforeTest(alwaysRun = true)
    public void initLoginPage() {
        driver = Hooks.getDriver();
        loginPage = new LoginPage(driver);
        discoverPage = new DiscoverPage(driver);
    }

    @Test(priority = 0, enabled = true, description = "Verify Reject flow", groups = {"Sanity"})
    public void verifyRejectTermsAndValidateMessage() {
        try {
            infoMessage("Launching Login Page validation");

            infoMessage("Waiting for Welcome page...");
            Assert.assertTrue(loginPage.waitForWelcomeScreen(), "Welcome page should load");
            passMessage("Welcome page loaded successfully");

            infoMessage("Validating all UI elements on Welcome page...");
            Assert.assertTrue(loginPage.isWelcomeViewFullyLoaded(), "All welcome UI should be visible");
            passMessage("All welcome UI elements are visible");

            infoMessage("Clicking Reject button...");
            loginPage.tapReject();
            passMessage("Tapped on Reject button successfully");

            infoMessage("Validating all UI elements on Reject flow page...");
            loginPage.isInfoTextAfterRejectDisplayed();
            passMessage("Reject Info Text is visible");

            infoMessage("Clicking Go Back button...");
            loginPage.tapGoBack();
            passMessage("Tapped on Go Back button successfully");

        } catch (AssertionError | Exception e) {
            failMessage(e);
            throw e;
        }
    }

    @Test(priority = 1, enabled = true, description = "Verify Login page elements ", groups = {"Sanity"})
    public void VerifyAcceptButtonNavigationToDiscoverPage() {

        try {
            Assert.assertTrue(loginPage.isWelcomeViewFullyLoaded(), "All welcome UI should be visible");
            passMessage("All welcome UI elements are visible");

            infoMessage("Clicking Accept button...");
            loginPage.tapAccept();
            passMessage("Tapped on Accept button successfully");

            // DiscoverPage should be visible
            Assert.assertTrue(discoverPage.waitForDiscoverPage(), "Discover tab should be visible");
            passMessage("Discover tab is visible successfully");

        } catch (AssertionError | Exception e) {
            failMessage(e);
            throw e;
        }
    }
}
