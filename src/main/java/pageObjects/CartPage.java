package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage{
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='top-menu']/li[2]")
    WebElement computerCategory;
    @FindBy(xpath = "//ul[@class='top-menu']/li[2]/ul/li[1]")
    WebElement computerSubCategory;
    @FindBy(xpath = "//div[@class='sub-category-grid']//div[@class='item-box'][1]")
    WebElement pickDesktopCategory;
    @FindBy(css = ".bar-notification")
    WebElement alertMessage;
    @FindBy(xpath = "//input[@value='18']")
    WebElement productHDDCheckbox;
    @FindBy(css = ".item-box")
    List<WebElement> products;
    @FindBy(xpath = "//div[@class='add-to-cart-panel']//input[@value='Add to cart']")
    WebElement addToCartPickedProductButton;
    @FindBy(className = "page-title")
    WebElement shoppingCartPageTitle;
    @FindBy(css = ".update-cart-button")
    WebElement updateShoppingCartPageButton;
    @FindBy(css = ".cart-item-row")
    List<WebElement> productsInCart;
    By addToCartButtons = By.cssSelector(".button-2");
    By addToCartButtonsFeaturedProducts = By.cssSelector(".product-box-add-to-cart-button");
    By removeProductsFromCartCheckbox = By.cssSelector("input[name='removefromcart']");

//    public WebElement getProductByName(String productName) {
//        return products.stream()
//                .filter(product -> product.getText().contains(productName)).findFirst().orElse(null);
//    }
    public boolean getAlertMessage() {
        return alertMessage.isDisplayed();
    }
    public void addProductToCartFromCategories(String productName) {
        computerCategory.click();
        pickDesktopCategory.click();
        WebElement product = getProductByName(products, productName);
        product.findElement(addToCartButtons).click();
        addToCartPickedProductButton.click();
        waitForElementToAppear(alertMessage);
    }
    public void addProductToCartFromFeaturedProducts(String productName) {
        WebElement product = getProductByName(products,productName);
        product.findElement(addToCartButtonsFeaturedProducts).click();
        waitForElementToAppear(alertMessage);
    }
    public void removeProductFromCart() {
        clickOnShoppingCartHeaderLink();
        for (WebElement cartItem:productsInCart) {
            cartItem.findElement(removeProductsFromCartCheckbox).click();
        }
        updateShoppingCartPageButton.click();
    }
    public void navigateToShoppingCartPage() {
        clickOnShoppingCartHeaderLink();
    }
    public String getShoppingCartPageTitle(){
        return shoppingCartPageTitle.getText();
    }
    public void addProductFromSubCategory(String productName) {
        Actions a = new Actions(driver);
        a.moveToElement(computerCategory).click(computerSubCategory).perform();
        WebElement product = getProductByName(products, productName);
        product.findElement(addToCartButtons).click();
        productHDDCheckbox.click();
        addToCartPickedProductButton.click();
        waitForElementToAppear(alertMessage);
    }
}
