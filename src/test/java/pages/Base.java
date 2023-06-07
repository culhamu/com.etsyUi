package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public abstract class Base {

    // abstract class oldugu icin nesnesi olusturulamaz.
    public Base() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}