package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends BasePage{
    WebDriver driver;
    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='small-searchterms' and @value='Search store']")
    WebElement searchField;
    @FindBy(css = ".search-box-button")
    WebElement searchButton;
    @FindBy(css = ".page-title")
    WebElement getPageTitle;
    @FindBy(css = ".item-box")
    List<WebElement> products;
    @FindBy(css = ".item-box")
    WebElement productsListItems;
    @FindBy(css = ".bar-notification")
    WebElement alertMessage;
    @FindBy(xpath = "//div[@class='search-results']/strong")
    WebElement noProductsFoundMessage;
    @FindBy(xpath = "//input[@value='18']")
    WebElement productHDDCheckbox;
    @FindBy(xpath = "//div[@class='add-to-cart-panel']//input[@value='Add to cart']")
    WebElement addToCartPickedProductButton;
    By addToCartButtons = By.cssSelector(".button-2");

    public WebElement getProductByName(String productName) {
        return products.stream()
                .filter(product -> product.getText().contains(productName)).findFirst().orElse(null);
    }
    public void enterTextInSearchField(String productName) {
        searchField.sendKeys(productName);
        searchButton.click();
    }
    public String getPageHeadingText() {
        return getPageTitle.getText();
    }
    public boolean isProductListVisible() {
        return productsListItems.isDisplayed();
    }
    public String getNoProductsMessageError() {
        return noProductsFoundMessage.getText();
    }
    public void addProductThroughSearchFieldInCart(String productName) {
        searchField.sendKeys(productName);
        searchButton.click();
        WebElement product = getProductByName(productName);
        product.findElement(addToCartButtons).click();
        productHDDCheckbox.click();
        addToCartPickedProductButton.click();
        waitForElementToAppear(alertMessage);
    }
}
