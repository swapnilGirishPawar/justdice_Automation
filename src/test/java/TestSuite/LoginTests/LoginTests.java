package TestSuite.LoginTests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTests {

    @BeforeTest
    public void beforeTest() {
        System.out.println("This is before test.");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("This is before method.");
    }

    @Test(priority = 0, description = "Verify Login Flow for new user", groups = {"Sanity"})
    public void verifyLoginFlow(){
        System.out.println("This is login Flow");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("This is after test.");
    }
}
