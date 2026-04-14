package TestSuite.ProfileTests;

import BaseClass.Hooks;
import Pages.Discover.DiscoverPage;
import Pages.Login.LoginPage;
import Pages.Profile.ProfilePage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProfileTests extends Hooks{
    private AppiumDriver driver;
    private ProfilePage profilePage;
    private DiscoverPage discoverPage;

    @BeforeTest(alwaysRun = true)
    public void initLoginPage() {
        driver = Hooks.getDriver();
        profilePage = new ProfilePage(driver);
        discoverPage = new DiscoverPage(driver);
    }


    @Test(priority = 0, enabled = true, description = "Verify Profile Page Navigation", groups = {"Sanity"})
    public void verifyProfilePageNavigation(){
        try{
            infoMessage("Tapping on Profile tab");
            discoverPage.tapProfileTab();
            passMessage("Tapped on Profile tab successfully");

            infoMessage("Validating all UI elements on Profile page...");
            Assert.assertTrue(profilePage.isProfileViewFullyLoaded(), "All UI elements should be visible");
            passMessage("All welcome UI elements are visible");

        } catch(AssertionError | Exception e){
            failMessage(e);
            throw e;
        }
    }

}
