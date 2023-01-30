//package pages;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class LoginPage extends BasePage {
//    private String url = "https://trello.com/login";
//    @FindBy(xpath = "//input[@name='user']")
//    private WebElement emailField;
//
//    @FindBy(xpath = "//input[contains(@class, 'account-button')]")
//    private WebElement continueButton;
//
//    @FindBy(xpath = "//input[contains(@name, 'password')]")
//    private WebElement passwordField;
//
//    @FindBy(xpath = "//button[@id='login-submit']")
//    private WebElement loginButton;
//
//    @FindBy(xpath = "//a[@class='js-login']")
//    private WebElement jsLoginButton;
//
//    public LoginPage() {
//        driver.get(url);
//        PageFactory.initElements(driver, this);
//    }
//
//    public HomePage authorization(String login, String password) {
//        int count = 0;
//        emailField.sendKeys(login);
//        continueButton.click();
//        while (count < 3) {
//            try {
//                passwordField.sendKeys(password);
//                loginButton.click();
//                if (jsLoginButton.isDisplayed()) authorization(login, password);
//            } catch (Exception ignored) {
//            }
//            count++;
//        }
//        return new HomePage();
//    }
//}
