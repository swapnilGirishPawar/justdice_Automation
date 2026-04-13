package Pages.Login;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage {
    @AndroidFindBy(accessibility = "Value")
    @iOSXCUITFindBy(id = "Value")
    private WebElement elementName;

}
