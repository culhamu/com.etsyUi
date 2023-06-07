package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

public class ReusableMethods {
    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }
    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }
    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }
    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }
    //   HARD WAIT WITH THREAD.SLEEP
//   waitFor(5);  => waits for 5 second
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                bekle(1);
            }
        }
    }
    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }
    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }
    public static void jsScrollBy(int scrollBy){
        JavascriptExecutor js=(JavascriptExecutor)Driver.getDriver();
        js.executeScript("window.scrollBy(0,"+scrollBy+")");
    }

    public static boolean titleListingMethod(String data){
        List<WebElement> actualList=Driver.getDriver().findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/thead/tr[1]//th"));
        List<String> stringActualList=new ArrayList<>();
        for (WebElement each:actualList) {
            stringActualList.add(each.getText());
        }
        System.out.println(stringActualList);

        String [] liste=data.split(", ");
        System.out.println(Arrays.toString(liste));
        int count=0;

        for (int i = 0; i <liste.length ; i++) {
            for (int j = 0; j <stringActualList.size() ; j++) {
                if (stringActualList.get(j).equals(liste[i])){
                    count++;
                }
            }
        }
        if (count==liste.length){
            return true;
        }
        return false;
    }

    public static boolean dashboardTitleListingMethod(Object data){
        List<WebElement> actualList=Driver.getDriver().findElements(By.xpath("//li[@class='treeview ']"));
        List<String> stringActualList=new ArrayList<>();
        for (WebElement each:actualList) {
            if (!each.getText().isBlank()){
                stringActualList.add(each.getText());
            }
        }

        System.out.println(stringActualList);
        for (String each:stringActualList) {
            if (each.equals(data)){
                return true;
            }
        }
      return false;
    }

    public static boolean stringListSortTesting(int sutunNo){
        Select select=new Select(Driver.getDriver().findElement(By.xpath("//select[@name='DataTables_Table_0_length']")));
        bekle(2);
        select.selectByVisibleText("All");
        bekle(2);
        WebElement title= Driver.getDriver().findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/thead/tr[1]//th["+sutunNo+"]"));
        title.click();
        ReusableMethods.bekle(2);
        List<WebElement> actualList=Driver.getDriver().findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td["+sutunNo+"]"));

        List<String> actualStringList=new ArrayList<>();
        for (WebElement each:actualList
        ) {
            actualStringList.add(each.getText().toLowerCase().replaceAll("[^a-zA-Z0-9\\\\s]","").replaceAll(" ","").replace("."," "));
        }
        List<String> ExpectedList=new ArrayList<>(actualStringList);
        Collections.sort(ExpectedList);
        System.out.println(actualStringList);
        System.out.println(ExpectedList);
        if (actualStringList.equals(ExpectedList)){
            return true;
        }
        return false;


    }

    public static boolean intListSortTesting(int sutunNo) {

        Select select = new Select(Driver.getDriver().findElement(By.xpath("//select[@name='DataTables_Table_0_length']")));
        ReusableMethods.bekle(3);
        select.selectByVisibleText("All");
        ReusableMethods.bekle(3);
        WebElement baslik = Driver.getDriver().findElement(By.xpath("//tr//th[" + sutunNo + "]"));
        baslik.click();
        ReusableMethods.bekle(3);
        List<WebElement> ActualList = Driver.getDriver().findElements(By.xpath("//tr//td[2]"));
        List<Integer> ActualStringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (!(ActualList.get(i).getText().isBlank())) {
                String data = ActualList.get(i).getText().replaceAll("[^0-9]", "");
                ActualStringList.add(Integer.parseInt(data));
            }
        }


        int sayi = 0;
        for (int i = 1; i < ActualStringList.size(); i++) {
            int ilkVeri = ActualStringList.get(sayi);
            if (ilkVeri > ActualStringList.get(i)) {
                return false;
            }
            sayi++;
        }

        return true;

    }

    public static boolean dateListSortTesting(int sutunNo) {
        Select select = new Select(Driver.getDriver().findElement(By.xpath("//select[@name='DataTables_Table_0_length']")));
        bekle(2);
        select.selectByVisibleText("All");
        bekle(2);
        WebElement baslik = Driver.getDriver().findElement(By.xpath("//*[@id='DataTables_Table_0']/thead/tr[1]//th[" + sutunNo + "]"));
        baslik.click();
        bekle(2);
        List<WebElement> ActualList = Driver.getDriver().findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[" + sutunNo + "]"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm a", Locale.ENGLISH);

        List<LocalDateTime> tarihler=new ArrayList<>();
        for (WebElement each:ActualList) {
            String data=each.getText();
            LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
            tarihler.add(dateTime);
        }

        int sayi=0;
        for (int i = 0; i <tarihler.size()-1 ; i++) {
            sayi=tarihler.get(i).compareTo(tarihler.get(i+1));
            if (sayi>0){
                return false;
            }

        }

        return true;
    }

    public static boolean headerTitleListingMethod(String data){
        List<WebElement> actualList=Driver.getDriver().findElements(By.xpath("//ul[@class='nav nav-tabs navlistscroll']//li"));
        List<String> stringActualList=new ArrayList<>();
        for (WebElement each:actualList) {
            stringActualList.add(each.getText());
        }
        String [] liste=data.split(", ");
        int count=0;

        for (int i = 0; i <liste.length ; i++) {
            for (int j = 0; j <stringActualList.size() ; j++) {
                if (stringActualList.get(j).contains(liste[i])){
                    count++;
                }
            }
        }
        if (count==liste.length){
            return true;
        }
        return false;
    }

    public static void switchToWindowHandle(Set<String> handles) {
        String handle = "";
        for (String each : handles) {
            if (!each.equals(handle)) {
                handle = each;
            }
        }
        Driver.getDriver().switchTo().window(handle);

    }



}
