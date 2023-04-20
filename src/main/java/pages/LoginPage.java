package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PropertiesUtils;

/**
 * The type Login page.
 */
public class LoginPage extends BasePage {

    private String url = PropertiesUtils.get("url.loginpage");

    /**
     * Поле ввода электронной почты
     */
    @FindBy(xpath = "//input[@name='user']")
    private WebElement emailField;

    /**
     * кнопка для продолжения авторизации, после нажатия появляется поле ввода пароля
     */
    @FindBy(xpath = "//input[contains(@class, 'account-button')]")
    private WebElement continueButton;

    /**
     * Поле ввода пароля
     */
    @FindBy(xpath = "//input[contains(@name, 'password')]")
    private WebElement passwordField;

    /**
     * Кнопка подтверждения авторизации
     */
    @FindBy(xpath = "//button[@id='login-submit']")
    private WebElement loginButton;

    /**
     * Кнопка для перехода на страницу авторизации.
     * Отображается если авторизация не прошла с первого раза
     */
    @FindBy(xpath = "//a[@class='js-login']")
    private WebElement jsLoginButton;

    /**
     * Instantiates a new Login page.
     */
    public LoginPage() {
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    /**
     * Авторизаця.
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return the home page
     */
    public HomePage authorization(String login, String password) {
        int count = 0;
        emailField.sendKeys(login);
        continueButton.click();
        while (count < 2) {
            //Авторизация не всегда проходит с первого раза, иногда после ввода логина и пароля
            //не происходит переход на домашнюю страницу, вместо этого отображается страница
            //с текстом "Вероятно, это приватная страница. Возможно, вы сможете её увидеть, выполнив вход."
            //после повторно ввода логина и пароля, осуществляется переход на домашнюю страницу
            try {
                passwordField.sendKeys(password);
                loginButton.click();
                if (jsLoginButton.isDisplayed()) authorization(login, password);
            } catch (Exception ignored) {
            }
            count++;
        }
        return new HomePage();
    }
}
