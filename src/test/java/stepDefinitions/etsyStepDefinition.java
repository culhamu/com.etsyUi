package stepDefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.Driver;

public class etsyStepDefinition {

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
}
