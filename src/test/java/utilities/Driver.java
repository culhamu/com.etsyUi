package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver(){

    }

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            String browserType = ConfigReader.getProperty("browser");

            switch (browserType) {
                case "chrome" -> {

                    //options.addArguments("--remote-allow-origins=*");
                    //Headless modu devre dışı
                    //options.setHeadless(false);

                    //options.setExperimentalOption("useAutomationExtension", false);

                    //Devtools
                    //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    //options.setExperimentalOption("useAutomationExtension", false);

                    //options.addArguments("--disable-save-password-bubble");

                    //options.addArguments("--disable-autofill-keyboard-accessory-view");
                    //options.addArguments("--disable-password-manager-reauthentication");

                    //options.addArguments("--disable-infobars");

                    //options.addArguments("--incognito");

                    //options.addArguments("enable-automation");

                    //User-Agent
                    //options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36");

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-notifications");
                    driverPool.set(new ChromeDriver(options));

                }
                case "firefox" -> driverPool.set(new FirefoxDriver());
                case "edge" -> driverPool.set(new EdgeDriver());
            }
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        return driverPool.get();

    }

    public static WebDriver getDriver(String browser) {

        if (driverPool.get() == null) {

            switch (browser) {
                case "chrome" -> driverPool.set(new ChromeDriver());
                case "firefox" -> driverPool.set(new FirefoxDriver());
                case "edge" -> driverPool.set(new EdgeDriver());
            }
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driverPool.get();
    }


    public static void closeDriver(){

        if (driverPool.get() != null){
            driverPool.get().close();
            driverPool.remove();
        }
    }
    public static void quitDriver(){

        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}