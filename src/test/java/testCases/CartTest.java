package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.LoginPage;

public class CartTest extends Base {
    WebDriver driver;
    public CartPage cartPage;
    public LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyAddingProductToCartFromCategories() {
        cartPage.addProductToCartFromCategories("Build your own expensive computer");
        Assert.assertTrue(cartPage.getAlertMessage());
    }
    @Test(priority = 2)
    public void verifyAddingProductToCartFromFeaturedProducts() {
        cartPage.addProductToCartFromFeaturedProducts("14.1-inch Laptop");
    }
    @Test(priority = 3)
    public void verifyNavigatingToShoppingCartHeaderLink() {
        cartPage.navigateToShoppingCartPage();
        Assert.assertEquals(cartPage.getShoppingCartPageTitle(), prop.getProperty("shoppingCartPageTitle"));
    }
    @Test(priority = 4)
    public void verifyRemovingProductFromShoppingCart() {
        cartPage.addProductToCartFromFeaturedProducts("14.1-inch Laptop");
        cartPage.removeProductFromCart();
    } @Test(priority = 5)
    public void verifyAddingProductFromSubCategories() {
        cartPage.addProductFromSubCategory("Build your own computer");
    }
}
