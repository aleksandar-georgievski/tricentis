package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".ico-login")
    WebElement loginHeaderLink;
    @FindBy(css = ".ico-cart")
    WebElement shoppingCartHeaderLink;

    public void clickOnLoginHeaderLink() {
        loginHeaderLink.click();
    }
    public void clickOnShoppingCartHeaderLink() {
        shoppingCartHeaderLink.click();
    }
    public void waitForElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public WebElement getProductByName(List<WebElement> products, String productName) {
        return products.stream()
                .filter(product -> product.getText().contains(productName)).findFirst().orElse(null);
    }
}
