package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    private String url = "https://trello.com/";
    @FindBy(xpath = "//a[contains(@href, 'login')]")
    private WebElement logInButton;

    public MainPage(){
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLogin(){
        logInButton.click();
        return new LoginPage();
    }
}
