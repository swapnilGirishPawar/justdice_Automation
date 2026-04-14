package Pages.Discover;


import Utils.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DiscoverPage{

    private final AppiumDriver driver;
    private final Duration defaultWait;

    public DiscoverPage(AppiumDriver driver, Duration defaultWait) {
        this.driver = driver;
        this.defaultWait = defaultWait;
        this.wait = new WebDriverWait(driver, defaultWait);
        PageFactory.initElements(new AppiumFieldDecorator(driver, defaultWait), this);
    }

    public DiscoverPage(AppiumDriver driver) {
        this(driver, Duration.ofSeconds(15));
    }

    private WebDriverWait wait;
    @AndroidFindBy(accessibility = "Discover")
    private WebElement tabDiscover;
    @AndroidFindBy(accessibility = "My Games")
    private WebElement tabMyGames;
    @AndroidFindBy(accessibility = "Profile")
    private WebElement tabProfile;
    @AndroidFindBy(id = "card")
    private WebElement completeSurveysCard;
    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Earn with apps\")")
    private WebElement rowEarnWithApps;
    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Earn with games\")")
    private WebElement rowEarnWithGames;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No apps currently available']")
    private WebElement lblNoAppsAvailable;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'VPN')]")
    private WebElement lblVpnMustBeDisabled;

    public void tapDiscoverTab() {
        tap(tabDiscover);
    }

    public void tapMyGamesTab() {
        tap(tabMyGames);
    }

    public void tapProfileTab() {
        tap(tabProfile);
    }

    public void tapCompleteSurveysCard() {
        tap(completeSurveysCard);
    }

    public void tapEarnWithApps() {
        tap(rowEarnWithApps);
    }

    public void tapEarnWithGames() {
        tap(rowEarnWithGames);
    }

    public boolean isDiscoverTabSelected() {
        return "true".equals(wait.until(ExpectedConditions.visibilityOf(tabDiscover)).getAttribute("selected"));
    }
    public boolean isNoAppsEmptyStateVisible() {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(lblNoAppsAvailable));
        return CommonUtils.isDisplayed(el);
    }

    public void assertNoAppsEmptyStateVisible() {
        assertVisible(lblNoAppsAvailable);
    }

    public void assertVpnDisabledHintVisible() {
        assertVisible(lblVpnMustBeDisabled);
    }

    public String getNoAppsMessageText() {
        return readVisibleText(lblNoAppsAvailable);
    }

    public String getVpnHintText() {
        return readVisibleText(lblVpnMustBeDisabled);
    }

    private void tap(WebElement target) {
        wait.until(ExpectedConditions.elementToBeClickable(target)).click();
    }

    private void assertVisible(WebElement target) {
        wait.until(ExpectedConditions.visibilityOf(target));
    }
    private String readVisibleText(WebElement target) {
        wait.until(ExpectedConditions.visibilityOf(target));
        return target.getText();
    }
    public void assertDiscoverTabSelected() {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(tabDiscover));
        String selected = el.getAttribute("selected");
        if (!"true".equals(selected)) {
            throw new AssertionError("Expected Discover tab selected; selected=" + selected);
        }
    }
    public boolean waitForDiscoverPage(Duration timeout) {
        try {
            waitFor(timeout).until(ExpectedConditions.visibilityOf(tabDiscover));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean waitForDiscoverPage() {
        return waitForDiscoverPage(defaultWait);
    }
    private WebDriverWait waitFor(Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }
}