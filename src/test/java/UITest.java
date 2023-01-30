import models.User;
import pages.elements.colors.BackgroundColors;
import pages.elements.colors.CoverColors;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.UserUtils;


public class UITest {
    private WebDriver driver;
    private MainPage mainPage;
    private HomePage homePage;
    private BoardPage boardPage;


    @BeforeClass
    public void before(){
        User user = UserUtils.initUser();
        driver = DriverManager.createDriver();
        BasePage.setDriver(driver);
        mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickLogin();
        loginPage.authorization(user.getEmail(), user.getPassword());
    }

    @AfterClass
    public void after(){
        boardPage.closeActiveBoard();
        driver.close();
        driver.quit();
    }

    @Test(testName = "Карточка в колонке",
    priority = 2,
    description = "Проверка отображения карточки в колонке")
    public void cardOnBoardTest() {
        homePage = new HomePage();
        BoardPage boardPage = homePage.openBoard("kanbantool");
        boardPage.clickOnCard("Карточка для изучения API");

        Assert.assertEquals(boardPage.getListName(), "Done", "Карточка находится в другой колонке");
    }

    @Test(testName = "Выполение чекбокса",
    description = "Проверка выполнения пунктов чекбоксов",
    dependsOnMethods = "cardOnBoardTest")
    public void completeCheckboxTest(){
        boardPage = new BoardPage();
        String firstCheckboxName = "Понять протокол HTTP";
        String secondCheckboxName = "Выучить методы запросов";

        Assert.assertTrue(boardPage.checklistCheckboxIsComplete(firstCheckboxName));
        Assert.assertTrue(boardPage.checklistCheckboxIsComplete(secondCheckboxName));
    }

    @Test(testName = "Установка обложки",
            description = "Проверка установки зеленой обложки для карточки",
            dependsOnMethods = "completeCheckboxTest")
    public void setCoverColorTest(){
        boardPage.setCoverColor(CoverColors.GREEN);

        Assert.assertTrue(boardPage.coverColorSelected(CoverColors.GREEN), "Выбран другой цвет");
    }

    @Test(testName = "Выполенение задачи",
            description = "Проверка выполнения задания в срок",
            dependsOnMethods = "setCoverColorTest")
    public void completeTaskTest(){
        boardPage.clickOnDueCheckbox();

        Assert.assertEquals(boardPage.getCardStatus(), "complete");
        boardPage.closeCard();
    }

    @Test(testName = "Установка фона",
            description = "Проверка установка зеленого фона",
            dependsOnMethods = "completeTaskTest")
    public void setBackgroundColorTest(){
        boardPage.openBackgroundColors()
                .setBackgroundColor(BackgroundColors.GREEN);

        Assert.assertTrue(boardPage.isBackgroundColor(BackgroundColors.GREEN));
    }

    @Test(testName = "Смена названия доски",
            description = "Проверка смены названия доски",
            dependsOnMethods = "setBackgroundColorTest")
    public void setNewBoardNameTest(){
        String newName = "Образование";
        boardPage.renameBoard(newName);

        Assert.assertEquals(boardPage.getBoardName(), newName);
    }
}
