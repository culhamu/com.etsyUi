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
    @Given("Kullanici {string} anasayfasina giris yapar")
    public void kullanici_anasayfasina_giris_yapar(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
    }

    @Given("Kullanici etsy anasayfasinin farkli {string} ile acilabildigini dogrular")
    public void kullaniciEtsyAnasayfasininFarkliIleAcilabildiginiDogrular(String browser) {

        Driver.getDriver(browser).get(ConfigReader.getProperty("etsyUrl"));

        String expectedUrl=ConfigReader.getProperty("etsyUrl");
        String actualUrl=Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
        Driver.closeDriver();
    }

    @Then("Kullanici login islemini tamamlar")
    public void kullaniciLoginIsleminiTamamlar() {
        etsyPage.login();
    }

    @And("Sepete urun ekler")
    public void sepeteUrunEkler() {
        etsyPage.addToCart();
    }

    @Then("Odeme islemlerini yapar")
    public void odemeIslemleriniYapar() {
        etsyPage.makePayment();
    }

    @And("Kart bilgilerini girer")
    public void kartBilgileriniGirer() {
        etsyPage.writeCardInformation();
    }
}
