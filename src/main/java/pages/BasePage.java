package pages;

import org.openqa.selenium.WebDriver;

/**
 * The type Base page.
 */
abstract public class BasePage {
    /**
     * The constant driver.
     */
    protected static WebDriver driver;

    /**
     * Set driver.
     *
     * @param webDriver the web driver
     */
    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }
}
