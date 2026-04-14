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

    @Test
    public void shouldShowEmptyGamesStateOnDiscover() {
        discover.tapDiscoverTab();
        discover.assertDiscoverTabSelected();
        discover.assertNoAppsEmptyStateVisible();
        discover.assertVpnDisabledHintVisible();
    }
    @Test
    public void shouldOpenSurveyFlowFromCard() {
        discover.tapCompleteSurveysCard();
        // add assertions on the next screen in that screen’s page object
    }
    @Test
    public void shouldOpenEarnSections() {
        discover.tapEarnWithApps();
        // assert next screen
        discover.tapEarnWithGames();
        // assert next screen
    }
}
