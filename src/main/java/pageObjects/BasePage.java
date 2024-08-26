package pageObjects;

import org.openqa.selenium.WebDriver;


public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
//    public String generateRandomEmail() {
//        LocalTime localDate = LocalTime.now();
//        return localDate.toString().replace(":", "");
//    }
}
