package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

public class EtsyPage extends Base{

    Actions actions=new Actions(Driver.getDriver());
    ReusableMethods reusableMethods=new ReusableMethods();

  @FindBy(xpath = "//button[@class='wt-btn wt-btn--small wt-btn--transparent wt-mr-xs-1 inline-overlay-trigger signin-header-action select-signin']")
    public WebElement signinHomePageButton;

    @FindBy(xpath = "//input[@id='join_neu_email_field']")
    public WebElement emailBox;

    @FindBy(xpath = "//*[@id=\"join_neu_password_field\"]")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@class=\"wt-btn wt-btn--primary wt-width-full\"]")
    public WebElement signinButton;

    @FindBy(xpath = "(//div[@class='height-placeholder'])[1]")
    public WebElement firstProduct;

    @FindBy(xpath = "//select[@id='variation-selector-0']")
    public WebElement selectOption;

    @FindBy(xpath = "//*[@id=\"variation-selector-1\"]/option[1]")
    public WebElement selectOption2;

    @FindBy(xpath = "//*[@id=\"listing-page-cart\"]/div[6]/div[1]/div[3]/div[2]/form/div/button")
    public WebElement addToCart;

    @FindBy(xpath = "//*[@id=\"listing-page-personalization-textarea\"]")
    public WebElement exampleBox;

    @FindBy(xpath = "//*[@id=\"atc-overlay-content\"]/div[2]/div/a/text()")
    public WebElement viewCartCheckout;

    @FindBy(xpath = "//*[@id=\"4b4c4c2_multiple_payment_method_credit_card\"]")
    public WebElement visaPayment;

    @FindBy(xpath = "//*[@id=\"multi-shop-cart-list\"]/div[1]/div/div[1]/div/div/div/form/div[2]/div[1]/button/span")
    public WebElement proceedCheckout;

    @FindBy(xpath = "//*[@id=\"country_id2-select\"]")
    public WebElement selectCountry;

    @FindBy(xpath = "//*[@id=\"name52-input\"]")
    public WebElement fullnameBox;

    @FindBy(xpath = "//*[@id=\"state48-select\"]")
    public WebElement selectProvince;

    @FindBy(xpath = "//*[@id=\"shipping-address-form\"]/div[2]/button/span[1]")
    public WebElement continuePayment;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/div[1]/div[1]/label")
    public WebElement cardRadioButton;

    @FindBy(xpath = "//*[@id=\"cc-name--paymentstep\"]")
    public WebElement paymentNameBox;

    @FindBy(xpath = "//input[@id='cc-num--paymentstep']")
    public WebElement paymentCardNumber;

    @FindBy(xpath = "//select[@id='expiration-month-select-648043f76078d']")
    public WebElement expirationDate;

    @FindBy(xpath = "//select[@id='expiration-year-select-648043f76078d']")
    public WebElement expirationYear;

    @FindBy(xpath = "//input[@id='cc-ccv--paymentstep']")
    public WebElement secureCode;

    @FindBy(xpath = "//button[@name='payment_submit']//span[contains(text(),'Review your order')]")
    public WebElement reviewYourOrderButton;

    @FindBy(xpath = "(//span[@class='etsy-icon wt-icon--smaller'])[1]")
    public WebElement crossButton;

    public void login(){
        signinHomePageButton.click();
        emailBox.click();
        emailBox.sendKeys(ConfigReader.getProperty("etsyEmail"));
        passwordBox.click();
        passwordBox.sendKeys(ConfigReader.getProperty("etsySifre"));
        ReusableMethods.bekle(2);
        actions.click(signinButton).pause(Duration.ofMillis(500)).build().perform();
    }
    public void addToCart(){
        actions.click(firstProduct).pause(Duration.ofMillis(1000)).build().perform();
        actions.click(firstProduct).pause(Duration.ofMillis(1000)).build().perform();
        Set<String> handle= Driver.getDriver().getWindowHandles();
        ReusableMethods.switchToWindowHandle(handle);
        actions.click(crossButton).pause(Duration.ofMillis(1000)).build().perform();

        actions.sendKeys(Keys.PAGE_DOWN).pause(Duration.ofMillis(500)).build().perform();
      Select select=new Select(selectOption);
      select.selectByIndex(1);
      ReusableMethods.bekle(2);

      select=new Select(selectOption2);
      select.selectByIndex(1);
      ReusableMethods.bekle(2);

        exampleBox.click();
        exampleBox.sendKeys("july");
        ReusableMethods.bekle(2);
        addToCart.click();
        ReusableMethods.bekle(2);
    }

}
