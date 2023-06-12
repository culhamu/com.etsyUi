package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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