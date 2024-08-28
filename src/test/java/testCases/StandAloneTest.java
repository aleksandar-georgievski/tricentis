package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalTime;

public class StandAloneTest {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(8));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        REGISTER
        LocalTime localDate = LocalTime.now();
        String generateEmail = localDate.toString().replace(":", "");

        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Aleksandar");
        driver.findElement(By.id("LastName")).sendKeys("Georgievski");
        driver.findElement(By.id("Email")).sendKeys("ace.georgievski"+ generateEmail +"@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("aleksandar");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("aleksandar");
        driver.findElement(By.id("register-button")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

//        SELECT A PRODUCT
        driver.findElement(By.xpath("//div[contains(@class, 'product-grid')]/descendant::div[2]")).click();
        driver.findElement(By.cssSelector(".recipient-name")).sendKeys("Stefan");
        driver.findElement(By.cssSelector(".recipient-email")).sendKeys("stefan.georgievski@gmail.com");
        driver.findElement(By.cssSelector(".add-to-cart-button")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".loading-image"))));
//        CART
        driver.findElement(By.xpath("//span[contains(@class, 'cart-label') and normalize-space(text()) = 'Shopping cart']")).click();
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.xpath("//button[@value='checkout']")).click();

//        CHECKOUT
        Actions actions = new Actions(driver);
        WebElement countryList = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
        actions.click(countryList).build().perform();
        actions.sendKeys("macedonia").perform();
        actions.click().perform();
        driver.findElement(By.cssSelector("#BillingNewAddress_City")).sendKeys("Atlanta");
        driver.findElement(By.cssSelector("#BillingNewAddress_Address1")).sendKeys("st.1");
        driver.findElement(By.cssSelector("#BillingNewAddress_Address2")).sendKeys("st.2");
        driver.findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode")).sendKeys("1111");
        driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys("1234567");
        driver.findElement(By.cssSelector("#BillingNewAddress_FaxNumber")).sendKeys("020202");
        driver.findElement(By.cssSelector(".new-address-next-step-button")).click();
//        PAYMENT METHOD
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".payment-method-next-step-button"))));
        driver.findElement(By.cssSelector(".payment-method-next-step-button")).click();
//        PAYMENT INFORMATION
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".payment-info-next-step-button"))));
        driver.findElement(By.cssSelector(".payment-info-next-step-button")).click();
//        CONFIRM ORDER
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".confirm-order-next-step-button"))));
        driver.findElement(By.cssSelector(".confirm-order-next-step-button")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#confirm-order-please-wait"))));
        String successHeadingMessage = driver.findElement(By.xpath("//div[@class='title']/strong")).getText();
        Assert.assertEquals(successHeadingMessage, "Your order has been successfully processed!");
        driver.findElement(By.cssSelector(".order-completed-continue-button")).click();




    }
}
