package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.EtsyPage;
import utilities.ConfigReader;
import utilities.Driver;

public class etsyStepDefinition {

    EtsyPage etsyPage=new EtsyPage();
    @Given("Kullanici {string} anasayfasina giris yapar")
    public void kullanici_anasayfasina_giris_yapar(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
    }

    @Given("Kullanici etsy anasayfasinin farkli {string} ile acilabildigini dogrular")
    public void kullaniciEtsyAnasayfasininFarkliIleAcilabildiginiDogrular(String browser) {

        Driver.getDriver(browser).get(ConfigReader.getProperty("etsyUrl"));

        String expectedUrl = "https://www.etsy.com/";

        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl);
    }

    @Then("Kullanici login islemini tamamlar")
    public void kullaniciLoginIsleminiTamamlar() {
        etsyPage.login();
    }

    @And("Sepete urun ekler")
    public void sepeteUrunEkler() {
        etsyPage.addToCart();
    }
}
