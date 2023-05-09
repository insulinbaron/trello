package pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

    /**
     * Save screenshot byte [ ].
     *
     * @return the byte [ ]
     */
    @Attachment(value = "screenshot", type="image/png")
    public static byte[] saveScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
