package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".ico-login")
    WebElement loginHeaderLink;

    public void clickOnLoginHeaderLink() {
        loginHeaderLink.click();
    }

//    public String generateRandomEmail() {
//        LocalTime localDate = LocalTime.now();
//        return localDate.toString().replace(":", "");
//    }
}
