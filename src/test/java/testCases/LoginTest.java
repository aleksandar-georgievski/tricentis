package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

public class LoginTest extends Base {
    WebDriver driver;
    public LoginPage loginPage;
    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        loginPage = new LoginPage(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyLoginWithProvidingValidCredentials() {
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
    }
    @Test(priority = 2)
    public void verifyLoginWithoutProvidingEmailIntoEmailField() {
        loginPage.login("", prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.checkIfLoginWarningMessageIsDisplayed(), prop.getProperty("loginWithInvalidEmail"));
    }
    @Test(priority = 3)
    public void verifyLoginWithoutProvidingPasswordIntoPasswordField() {
        loginPage.login(prop.getProperty("validEmail"), "");
        Assert.assertEquals(loginPage.checkIfLoginWarningMessageIsDisplayed(), prop.getProperty("loginWithInvalidPassword"));
    }
    @Test(priority = 4)
    public void verifyLoginWithoutProvidingAnyCredentialsIntoFields() {
        loginPage.login("", "");
        Assert.assertEquals(loginPage.checkIfLoginWarningMessageIsDisplayed(), prop.getProperty("loginWithInvalidEmail"));
    }
}
