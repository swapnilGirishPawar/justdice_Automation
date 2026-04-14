package Pages.Login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final AppiumDriver driver;
    private final Duration defaultWait;

    public LoginPage(AppiumDriver driver, Duration defaultWait) {
        this.driver = driver;
        this.defaultWait = defaultWait;
        PageFactory.initElements(new AppiumFieldDecorator(driver, defaultWait), this);
    }

    public LoginPage(AppiumDriver driver) {
        this(driver, Duration.ofSeconds(15));
    }

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@scrollable='true']")
    @iOSXCUITFindBy(id = "Value")
    private WebElement welcomeScrollView;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@scrollable='true']//android.widget.ImageView")
    @iOSXCUITFindBy(id = "Value")
    private WebElement welcomeHeroImage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome to Cash Giraffe!']")
    @iOSXCUITFindBy(id = "Value")
    private WebElement welcomeTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Play games and earn money or gift cards!']")
    @iOSXCUITFindBy(id = "Value")
    private WebElement welcomeTagline;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Complete sign up and earn ']")
    @iOSXCUITFindBy(id = "Value")
    private WebElement signupBonusPrefixLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='720']")
    @iOSXCUITFindBy(id = "Value")
    private WebElement signupBonusAmount;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Terms of Service')]")
    @iOSXCUITFindBy(id = "Value")
    private WebElement termsOfServiceNotice;

    @AndroidFindBy(accessibility = "Accept")
    @iOSXCUITFindBy(id = "Value")
    private WebElement acceptButton;

    @AndroidFindBy(accessibility = "Reject")
    @iOSXCUITFindBy(id = "Value")
    private WebElement rejectButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Cash Giraffe collects data')]")
    @iOSXCUITFindBy(id = "Value")
    private WebElement installedAppsDataCollectionNotice;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Adjoe GmbH')]")
    @iOSXCUITFindBy(id = "Value")
    private WebElement adjoePersonalizedRecommendationsNotice;

    @AndroidFindBy(accessibility = "Go back")
    @iOSXCUITFindBy(id = "Value")
    private WebElement goBackButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"To proceed, you need to accept our Terms of Service\"]")
    @iOSXCUITFindBy(id = "Value")
    private WebElement infoTextAfterReject;



    private WebDriverWait waitFor(Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }

    private boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean waitForWelcomeScreen() {
        return waitForWelcomeScreen(defaultWait);
    }

    public boolean waitForWelcomeScreen(Duration timeout) {
        try {
            waitFor(timeout).until(ExpectedConditions.visibilityOf(welcomeTitle));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isInfoTextAfterRejectDisplayed() {
        return isDisplayed(infoTextAfterReject);
    }


    public boolean isWelcomeScrollViewDisplayed() {
        return isDisplayed(welcomeScrollView);
    }

    public boolean isWelcomeHeroImageDisplayed() {
        return isDisplayed(welcomeHeroImage);
    }

    public boolean isWelcomeTitleDisplayed() {
        return isDisplayed(welcomeTitle);
    }

    public boolean isWelcomeTaglineDisplayed() {
        return isDisplayed(welcomeTagline);
    }

    public boolean isSignupBonusSectionDisplayed() {
        return isDisplayed(signupBonusPrefixLabel) && isDisplayed(signupBonusAmount);
    }

    public boolean isTermsOfServiceNoticeDisplayed() {
        return isDisplayed(termsOfServiceNotice);
    }

    public boolean isAcceptButtonDisplayed() {
        return isDisplayed(acceptButton);
    }

    public boolean isRejectButtonDisplayed() {
        return isDisplayed(rejectButton);
    }

    public boolean isDataCollectionDisclaimerDisplayed() {
        return isDisplayed(installedAppsDataCollectionNotice);
    }

    public boolean isAdjoeNoticeDisplayed() {
        return isDisplayed(adjoePersonalizedRecommendationsNotice);
    }

    public boolean isWelcomeViewFullyLoaded() {
        return waitForWelcomeScreen()
                && isWelcomeHeroImageDisplayed()
                && isWelcomeTaglineDisplayed()
                && isSignupBonusSectionDisplayed()
                && isTermsOfServiceNoticeDisplayed()
                && isAcceptButtonDisplayed()
                && isRejectButtonDisplayed()
                && isDataCollectionDisclaimerDisplayed()
                && isAdjoeNoticeDisplayed();
    }

    public String getWelcomeTitleText() {
        return welcomeTitle.getText();
    }

    public String getWelcomeTaglineText() {
        return welcomeTagline.getText();
    }

    public String getSignupBonusAmountText() {
        return signupBonusAmount.getText();
    }

    public String getTermsOfServiceNoticeText() {
        return termsOfServiceNotice.getText();
    }

    public String getDataCollectionDisclaimerText() {
        return installedAppsDataCollectionNotice.getText();
    }

    public String getAdjoeNoticeText() {
        return adjoePersonalizedRecommendationsNotice.getText();
    }

    public void tapAccept() {
        WebElement target = waitFor(defaultWait)
                .until(ExpectedConditions.elementToBeClickable(acceptButton));
        target.click();
    }

    public void tapReject() {
        WebElement target = waitFor(defaultWait)
                .until(ExpectedConditions.elementToBeClickable(rejectButton));
        target.click();
    }
    public void tapGoBack() {
        WebElement target = waitFor(defaultWait)
                .until(ExpectedConditions.elementToBeClickable(goBackButton));
        target.click();
    }

}
