package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#Email")
    WebElement loginEmailField;
    @FindBy(css = "#Password")
    WebElement loginPasswordField;
    @FindBy(css = ".login-button")
    WebElement loginButton;
    @FindBy(css = ".validation-summary-errors li")
    WebElement loginWarningMessage;
    @FindBy(css = "div[class='header-links'] a[class='account']")
    WebElement emailInHeaderSection;

    public void login(String emailText, String passwordText) {
        clickOnLoginHeaderLink();
        loginEmailField.sendKeys(emailText);
        loginPasswordField.sendKeys(passwordText);
        loginButton.click();
    }
    public String getTheEmailInHeaderSection() {
        return emailInHeaderSection.getText();
    }
    public String checkIfLoginWarningMessageIsDisplayed() {
        return loginWarningMessage.getText();
    }
}
