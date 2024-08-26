package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Properties;

public class Base {
    WebDriver driver;
    public Properties prop;
    public Base() {
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(propFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateRandomEmail() {
        LocalTime localDate = LocalTime.now();
        String dateEmail = localDate.toString().replace(":", "");
        return "ace" + dateEmail + "@gmail.com";
    }

    public WebDriver initializeBrowserAndOpenApplication(String browserName) {
        if(browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(8));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(prop.getProperty("url"));
        return driver;
    }
}