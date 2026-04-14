package Pages.Profile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static Utils.CommonUtils.isDisplayed;

public class ProfilePage {
    private final AppiumDriver driver;
    private final Duration defaultWait;

    public ProfilePage(AppiumDriver driver, Duration defaultWait) {
        this.driver = driver;
        this.defaultWait = defaultWait;
        PageFactory.initElements(new AppiumFieldDecorator(driver, defaultWait), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"About\"]")
    @iOSXCUITFindBy(id = "Value")
    private WebElement aboutSection;

    @AndroidFindBy(accessibility = "Customer Support")
    @iOSXCUITFindBy(id = "Value")
    private WebElement CustomerSupport;

    public ProfilePage(AppiumDriver driver) {
        this(driver, Duration.ofSeconds(15));
    }
    public boolean isProfileViewFullyLoaded(){
        return isAboutSectionDisplayed()
                && isCustomerSupportDisplayed();
    }
    public boolean isAboutSectionDisplayed() {
        return isDisplayed(aboutSection);
    }
    public boolean isCustomerSupportDisplayed() {
        return isDisplayed(CustomerSupport);
    }

}
