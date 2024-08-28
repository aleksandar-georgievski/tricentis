package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class CheckoutTest extends Base {
    WebDriver driver;
    public CartPage cartPage;
    public LoginPage loginPage;
    public SearchPage searchPage;
    public CheckoutPage checkoutPage;
    public RegisterPage registerPage;
    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        registerPage = new RegisterPage(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyNavigatingToCheckoutWithoutAddingProductToCart() {
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
        Assert.assertEquals(cartPage.navigateToShoppingCartPageWithoutProduct(), prop.getProperty("shoppingCartEmpty"));
    }
    @Test(priority = 2)
    public void verifyAddingProductAndNavigatingToCheckoutWithoutAcceptingTermsAndService() {
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
       cartPage.navigateToShoppingCartWithHavingAProduct("14.1-inch Laptop");
    }
    @Test(priority = 3)
    public void verifyAddingProductAndNavigatingToCheckoutWithoutEnteringBillingInformation() {
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
        cartPage.navigateToCheckoutSection("14.1-inch Laptop");
        checkoutPage.billingAddressDropDown();
        checkoutPage.clickOnBillingAddressContinueButton();
        Assert.assertTrue(checkoutPage.getErrorMessages());
    }
    @Test(priority = 4)
    public void verifyCheckoutWithoutBeingLoggedIn() {
        cartPage.navigateToCheckoutSection("14.1-inch Laptop");
        Assert.assertEquals(registerPage.getRegisterPageTitle(), prop.getProperty("pleaseSignIn"));
    }
    @Test(priority = 5)
    public void verifyCheckoutWithBeingLoggedInAsValidUser() {
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
        cartPage.navigateToCheckoutSection("14.1-inch Laptop");
        checkoutPage.billingAddressDropDown();
        checkoutPage.checkout("Skopje", "ul.1", "ul.2", "1000", "077077077", "02-020-020");
        Assert.assertTrue(checkoutPage.getCheckoutSuccessMessage().contains(prop.getProperty("checkoutCompleted")));
        checkoutPage.orderCompleteContinueButton();
    }

}










