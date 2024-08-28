package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage{
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//select[@id='billing-address-select']")
    WebElement selectAddress;
    @FindBy(css = ".field-validation-error")
    WebElement errorMessages;
    @FindBy(xpath = "//select[@id='BillingNewAddress_CountryId']")
    WebElement countryDropDownField;
    @FindBy(css = "#BillingNewAddress_City")
    WebElement cityAddressField;
    @FindBy(css = "#BillingNewAddress_Address1")
    WebElement firstAddressField;
    @FindBy(css = "#BillingNewAddress_Address2")
    WebElement secondAddressField;
    @FindBy(css = "#BillingNewAddress_ZipPostalCode")
    WebElement postalCodeField;
    @FindBy(css = "#BillingNewAddress_PhoneNumber")
    WebElement phoneNumberField;
    @FindBy(css = "#BillingNewAddress_FaxNumber")
    WebElement faxNumberField;
    @FindBy(css = ".new-address-next-step-button")
    WebElement billingAddressContinueButton;
    @FindBy(xpath = "//div[@id='shipping-buttons-container']/input")
    WebElement shippingAddressContinueButton;
    @FindBy(css = ".shipping-method-next-step-button")
    WebElement shippingMethodContinueButton;
    @FindBy(css = ".payment-method-next-step-button")
    WebElement paymentMethodContinueButton;
    @FindBy(css = ".payment-info-next-step-button")
    WebElement paymentInfoContinueButton;
    @FindBy(css = ".confirm-order-next-step-button")
    WebElement confirmOrderContinueButton;
    @FindBy(xpath = "//div[@class='title']/strong")
    WebElement checkoutSuccessMessage;
    @FindBy(css = ".order-completed-continue-button")
    WebElement orderCompleteButton;

    public void checkout(String cityAddress, String firstAddress, String secondAddress, String postalCode, String phoneNumber, String faxNumber) {
        Actions a = new Actions(driver);
        a.click(countryDropDownField).build().perform();
        a.sendKeys("macedonia").perform();
        cityAddressField.sendKeys(cityAddress);
        firstAddressField.sendKeys(firstAddress);
        secondAddressField.sendKeys(secondAddress);
        postalCodeField.sendKeys(postalCode);
        phoneNumberField.sendKeys(phoneNumber);
        faxNumberField.sendKeys(faxNumber);
        billingAddressContinueButton.click();
        waitForElementToAppear(shippingAddressContinueButton);
        shippingAddressContinueButton.click();
        waitForElementToAppear(shippingMethodContinueButton);
        shippingMethodContinueButton.click();
        waitForElementToAppear(paymentMethodContinueButton);
        paymentMethodContinueButton.click();
        waitForElementToAppear(paymentInfoContinueButton);
        paymentInfoContinueButton.click();
        waitForElementToAppear(confirmOrderContinueButton);
        confirmOrderContinueButton.click();
    }
    public String getCheckoutSuccessMessage() {
        return checkoutSuccessMessage.getText();
    }
    public void orderCompleteContinueButton() {
        orderCompleteButton.click();
    }
    public boolean getErrorMessages() {
        return errorMessages.isDisplayed();
    }

    public void billingAddressDropDown() {
        Select select = new Select(selectAddress);
        select.selectByVisibleText("New Address");
    }
    public void clickOnBillingAddressContinueButton() {
        billingAddressContinueButton.click();
    }

}
