package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;

public class SearchTest extends Base {
    WebDriver driver;
    public CartPage cartPage;
    public LoginPage loginPage;
    public SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.getTheEmailInHeaderSection(), prop.getProperty("validEmail"));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifySearchingWithExistingProduct() {
        searchPage.enterTextInSearchField("computer");
        Assert.assertEquals(searchPage.getPageHeadingText(), prop.getProperty("searchPageTitle"));
        Assert.assertTrue(searchPage.isProductListVisible());
    }
    @Test(priority = 2)
    public void verifySearchingWithNonExistingProduct() {
        searchPage.enterTextInSearchField("mac");
        Assert.assertEquals(searchPage.getPageHeadingText(), prop.getProperty("searchPageTitle"));
        Assert.assertEquals(searchPage.getNoProductsMessageError(), prop.getProperty("noProductsFound"));
    }
    @Test(priority = 3)
    public void verifySearchingWithoutProvidingAnyProduct() {
        searchPage.enterTextInSearchField("");
        driver.switchTo().alert().accept();
    }
    @Test(priority = 4)
    public void verifyAddingProductToCartFromSearchPage() {
        searchPage.addProductThroughSearchFieldInCart("Build your own computer");
    }
}
