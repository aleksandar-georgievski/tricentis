package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.RegisterPage;

public class RegisterTest extends Base {
    WebDriver driver;
    public RegisterPage registerPage;
    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        registerPage = new RegisterPage(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyRegisteringAnAccountByProvidingAllFields() {
        registerPage.register(prop.getProperty("firstName"), prop.getProperty("lastName"), generateRandomEmail(), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
        registerPage.clickOnRegisterConfirmButton();
        Assert.assertTrue(registerPage.checkIfHeaderLogoIsDisplayed());
    }
    @Test(priority = 2)
    public void verifyRegisteringAccountWithoutProvidingConfirmPasswordField() {
        registerPage.register(prop.getProperty("firstName"), prop.getProperty("lastName"), generateRandomEmail(), prop.getProperty("validPassword"), "");
        Assert.assertEquals(registerPage.getErrorMessageText(), prop.getProperty("passwordRequiredError"));
    }
    @Test(priority = 3)
    public void verifyRegisteringAccountWithoutProvidingAnyFields() {
        registerPage.register("", "", "", "", "");
        Assert.assertTrue(registerPage.checkErrorMessage());
    }
    @Test(priority = 4)
    public void verifyRegisteringAccountWithPasswordLessCharactersThanRequired() {
        registerPage.register(prop.getProperty("firstName"), prop.getProperty("lastName"), generateRandomEmail(), prop.getProperty("invalidPassword"), prop.getProperty("invalidPassword"));
        Assert.assertEquals(registerPage.getErrorMessageText(), prop.getProperty("invalidPasswordLessCharactersError"));
    }
    @Test(priority = 5)
    public void verifyRegisteringWithEmailThatAlreadyExist() {
        registerPage.register(prop.getProperty("firstName"), prop.getProperty("lastName"), prop.getProperty("email"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
        Assert.assertEquals(registerPage.getEmailExistErrorMessageText(), prop.getProperty("emailAlreadyExistError"));
    }
}





