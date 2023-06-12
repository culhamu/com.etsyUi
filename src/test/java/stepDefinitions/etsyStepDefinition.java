package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import pages.EtsyPage;
import utilities.ConfigReader;
import utilities.Driver;
import java.time.Duration;


public class etsyStepDefinition {

    WebDriver driver;
    EtsyPage etsyPage=new EtsyPage();


    @And("Kart bilgilerini girer")
    public void kartBilgileriniGirer() {

    }

    @Given("User checks etsy homepage can be opened with different {string}")
    public void userChecksEtsyHomepageCanBeOpenedWithDifferent(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
    }

    @Given("User logs into {string} homepage")
    public void userLogsIntoHomepage(String browser) {
        if (browser.equals("chrome")){
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }else {
            driver=new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        driver.get(ConfigReader.getProperty("etsyUrl"));
        String expectedUrl="https://www.etsy.com/";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
        driver.close();
    }

    @Then("User completes the login process")
    public void userCompletesTheLoginProcess() {
        etsyPage.login();
    }

    @And("User adds the product to the shopping cart")
    public void userAddsTheProductToTheShoppingCart() {
        etsyPage.addToCart();
    }

    @Then("User makes payment transactions")
    public void userMakesPaymentTransactions() {
        etsyPage.makePayment();
    }

    @And("User enters Card information")
    public void userEntersCardInformation() {
        etsyPage.writeCardInformation();
    }

    @Then("User closes the browser")
    public void userClosesTheBrowser() {
        Driver.quitDriver();
    }
}
