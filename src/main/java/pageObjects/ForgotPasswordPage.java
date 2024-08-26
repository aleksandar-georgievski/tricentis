package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BasePage{
    WebDriver driver;
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='forgot-password']/a")
    WebElement forgotPasswordLink;
    @FindBy(css = ".page-title")
    WebElement forgottenPasswordPageHeading;
    @FindBy(css = "#Email")
    WebElement emailAddressField;
    @FindBy(css = ".password-recovery-button")
    WebElement recoverButton;
    @FindBy(css = ".page-body .result")
    WebElement successMessageForEmailSent;

    public void clickOnForgotPasswordLinkText() {
        clickOnLoginHeaderLink();
        forgotPasswordLink.click();
    }
    public String getForgottenPasswordPageHeading() {
        return forgottenPasswordPageHeading.getText();
    }
    public void getEmailAddressField(String emailText) {
        emailAddressField.sendKeys(emailText);
    }
    public void clickOnRecoverButton() {
        recoverButton.click();
    }
    public String getSuccessMessage() {
        return successMessageForEmailSent.getText();
    }
}
