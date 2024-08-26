package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {
    WebDriver driver;
    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Register")
    WebElement registerHeadingLink;
    @FindBy(id = "gender-male")
    WebElement genderOptionMale;
    @FindBy(id = "FirstName")
    WebElement firstName;
    @FindBy(id = "LastName")
    WebElement lastName;
    @FindBy(id = "Email")
    WebElement email;
    @FindBy(id = "Password")
    WebElement password;
    @FindBy(id = "ConfirmPassword")
    WebElement confirmPassword;
    @FindBy(id = "register-button")
    WebElement registerButton;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement confirmRegistrationButton;
    @FindBy(xpath = "//span[@class='field-validation-error']/span")
    WebElement textFieldEmptyErrorMessage;
    @FindBy(xpath = "//div[@class='validation-summary-errors']/ul/li")
    WebElement emailAlreadyExistErrorMessage;
    @FindBy(css = ".header-logo")
    WebElement headerLogo;

    public void clickOnRegisterHeadingLink() {
        registerHeadingLink.click();
    }
    public void selectGenderOption() {
        genderOptionMale.click();
    }
    public void enterFirstName(String firstNameText) {
        firstName.sendKeys(firstNameText);
    }
    public void enterLastName(String lastNameText) {
        lastName.sendKeys(lastNameText);
    }
    public void enterEmail(String emailText) {
        email.sendKeys(emailText);
    }
    public void enterPassword(String passwordText) {
        password.sendKeys(passwordText);
    }
    public void enterConfirmPassword(String confirmPasswordText) {
        confirmPassword.sendKeys(confirmPasswordText);
    }
    public void clickOnRegisterButton() {
        registerButton.click();
    }
    public void clickOnRegisterConfirmButton() {
        confirmRegistrationButton.click();
    }
    public String getErrorMessageText() {
        return textFieldEmptyErrorMessage.getText();
    }
    public boolean checkErrorMessage() {
        textFieldEmptyErrorMessage.isDisplayed();
        return true;
    }
    public String getEmailExistErrorMessageText() {
        return emailAlreadyExistErrorMessage.getText();
    }
    public boolean checkIfHeaderLogoIsDisplayed() {
        return headerLogo.isDisplayed();
    }

    public void register(String firstNameText, String lastNameText, String emailText, String passwordText, String confirmPasswordText) {
        registerHeadingLink.click();;
        genderOptionMale.click();
        firstName.sendKeys(firstNameText);
        lastName.sendKeys(lastNameText);
        email.sendKeys(emailText);
        password.sendKeys(passwordText);
        confirmPassword.sendKeys(confirmPasswordText);
        registerButton.click();
//        confirmRegistrationButton.click();
    }

}
