package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PropertiesUtils;

/**
 * The type Main page.
 */
public class MainPage extends BasePage {
    private String url = PropertiesUtils.get("url.mainpage");

    /**
     * Кнопка для прохождения авторизации.
     * После нажатия будет открыта страница login page
     */
    @FindBy(xpath = "//a[contains(@href, 'login')]")
    private WebElement logInButton;

    /**
     * Instantiates a new Main page.
     */
    public MainPage(){
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    /**
     * Нажать кнопку для прохождения авторизации.
     *
     * @return the login page
     */
    public LoginPage clickLogin(){
        logInButton.click();
        return new LoginPage();
    }
}
