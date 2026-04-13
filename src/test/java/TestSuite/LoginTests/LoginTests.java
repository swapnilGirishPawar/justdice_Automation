package TestSuite.LoginTests;

import BaseClass.Hooks;
import Pages.Login.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTests extends Hooks {

    @Test(priority = 0, description = "Trial Test Check ", groups = {"Sanity"})
    public void verifyLoginFlow(){
        System.out.println("This Is Trial Test Check");
        passMessage("This test is passed");
    }

    @Test(priority = 1, description = "Verify Login page elements ", groups = {"Sanity"})
    public void shouldShowWelcomeAndAcceptTerms() {

        AppiumDriver driver = Hooks.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        try {
            infoMessage("Launching Login Page validation");

            infoMessage("Waiting for Welcome Screen...");
            Assert.assertTrue(loginPage.waitForWelcomeScreen(), "Welcome screen should load");
            passMessage("Welcome screen loaded successfully");

            infoMessage("Validating all UI elements on Welcome Screen...");
            Assert.assertTrue(loginPage.isWelcomeViewFullyLoaded(), "All welcome UI should be visible");
            passMessage("All welcome UI elements are visible");

            infoMessage("Clicking Accept button...");
            loginPage.tapAccept();
            passMessage("Tapped on Accept button successfully");

        } catch (AssertionError | Exception e) {
            failMessage(e);
            throw e;
        }
    }
}
