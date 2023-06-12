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

    @FindBy(xpath = "//select[@id='variation-selector-1']")
    public WebElement selectOption2;

    @FindBy(xpath = "(//button[@class='wt-btn wt-btn--filled wt-width-full'])[1]")
    public WebElement addToCart;

    @FindBy(xpath = "(//*[@class='wt-textarea wt-textarea'])[1]")
    public WebElement exampleBox;

    @FindBy(xpath = "(//*[@class='wt-btn wt-width-full wt-btn--primary'])[1]")
    public WebElement viewCartCheckout;

    @FindBy(xpath = "(//button[@type='button'])[3]")
    public WebElement editButton;
    @FindBy(xpath = "(//input[@id='401c7c1_multiple_payment_method_credit_card'])[1]")
    public WebElement visaPayment;

    @FindBy(xpath = "(//span[@class='submit-button-text'])[1]")
    public WebElement proceedCheckout;

    @FindBy(xpath = "//select[@id='country_id27-select']")
    public WebElement selectCountry;

    @FindBy(xpath = "(//button[@class='select_btn wt-btn wt-btn--filled wt-width-full'])[1]")
    public WebElement shipHereButton;

    @FindBy(xpath = "//select[@id='state33-select']")
    public WebElement selectProvince;

    @FindBy(xpath = "//button[@class='wt-btn wt-btn--filled address-save-clicked confirm-btn']")
    public WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"shipping-address-form\"]/div[2]/button/span[1]")
    public WebElement continuePayment;

    @FindBy(xpath = "//input[@id='cc-radio--paymentstep']")
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
        if (Driver.getDriver().getWindowHandle().contains("Select an option")){
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
        actions.click(viewCartCheckout).pause(Duration.ofMillis(500)).build().perform();}
        else {
            ReusableMethods.bekle(2);
            addToCart.click();
            ReusableMethods.bekle(2);
            actions.click(viewCartCheckout).pause(Duration.ofMillis(500)).build().perform();
        }
    }

  public void makePayment() {

      proceedCheckout.click();
      ReusableMethods.bekle(2);
      editButton.click();
      ReusableMethods.bekle(2);
      actions.sendKeys(Keys.TAB,Keys.ENTER)
              .sendKeys(Keys.TAB).pause(Duration.ofMillis(500))
              .sendKeys("Mustafa Culha")
              .sendKeys(Keys.TAB)
              .sendKeys("Merter").pause(Duration.ofMillis(500))
              .sendKeys(Keys.TAB)
              .sendKeys("Gedikli").pause(Duration.ofMillis(500))
              .sendKeys(Keys.TAB)
              .sendKeys("34810").pause(Duration.ofMillis(500))
              .sendKeys(Keys.TAB)
              .sendKeys("Bahcelievler").pause(Duration.ofMillis(500))
              .sendKeys(Keys.TAB)
              .click()
              .build().perform();

      saveButton.click();
      ReusableMethods.bekle(2);

  }

    public void writeCardInformation() {

        shipHereButton.click();
        ReusableMethods.bekle(3);
        actions.click(cardRadioButton).build().perform();
        ReusableMethods.bekle(1);
        actions.sendKeys(Keys.TAB).pause(Duration.ofMillis(500))
                .sendKeys("Mustafa Culha").pause(Duration.ofMillis(500))
                .sendKeys(Keys.TAB).pause(Duration.ofMillis(500))
                .sendKeys("4242424242424242").pause(Duration.ofMillis(500))
                .sendKeys(Keys.TAB,Keys.ENTER).pause(Duration.ofMillis(500))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).pause(Duration.ofMillis(500))
                .sendKeys(Keys.TAB,Keys.ENTER).pause(Duration.ofMillis(500))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).pause(Duration.ofMillis(500))
                .sendKeys(Keys.TAB).pause(Duration.ofMillis(500)).build().perform();

        actions.click(secureCode).build().perform();
        secureCode.sendKeys("854");
        reviewYourOrderButton.click();

    }
}
