package Pages.Discover;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class DiscoverPage {
    @AndroidFindBy(accessibility = "Value")
    @iOSXCUITFindBy(id = "Value")
    private WebElement elementName;
}
