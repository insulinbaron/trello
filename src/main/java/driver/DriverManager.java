package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * The type Driver manager.
 */
public class DriverManager {
    /**
     * The constant INSTANCE - pre-created class instance.
     */
    private static final DriverManager INSTANCE = new DriverManager();
    private static WebDriver driver;
    private DriverManager(){
    }

    public static DriverManager getInstance(){
        return INSTANCE;
    }

    static {
        driver = createChromeDriver();
    }

    private static WebDriver createChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920x1080");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    /**
     * Возвращает экземпляр драйвера
     *
     * @return the web driver
     */
    public static WebDriver getDriver(){
        return driver;
    }

}
