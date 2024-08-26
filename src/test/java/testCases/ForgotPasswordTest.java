package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ForgotPasswordPage;

public class ForgotPasswordTest extends Base {
    WebDriver driver;
    public ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickOnForgotPasswordLinkText();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyFunctionalityOfForgotPasswordLinkText() {
        Assert.assertEquals(forgotPasswordPage.getForgottenPasswordPageHeading(), "Password recovery");
    }
    @Test(priority = 2)
    public void verifyPasswordRecoveryByProvidingValidEmailAddress() {
        Assert.assertEquals(forgotPasswordPage.getForgottenPasswordPageHeading(), "Password recovery");
        forgotPasswordPage.getEmailAddressField(prop.getProperty("validEmail"));
        forgotPasswordPage.clickOnRecoverButton();
        Assert.assertEquals(forgotPasswordPage.getSuccessMessage(), prop.getProperty("emailFound"));
    }
    @Test(priority = 3)
    public void verifyPasswordRecoveryByProvidingInvalidEmailAddress() {
        Assert.assertEquals(forgotPasswordPage.getForgottenPasswordPageHeading(), "Password recovery");
        forgotPasswordPage.getEmailAddressField(generateRandomEmail());
        forgotPasswordPage.clickOnRecoverButton();
        Assert.assertEquals(forgotPasswordPage.getSuccessMessage(), prop.getProperty("emailNotFound"));

    }

}
