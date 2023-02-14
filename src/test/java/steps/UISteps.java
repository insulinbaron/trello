package steps;

import driver.DriverManager;
import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import pages.elements.colors.BackgroundColors;
import pages.elements.colors.CoverColors;
import utils.UserUtils;


/**
 * The type Ui steps.
 */
public class UISteps {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static HomePage homePage;
    private static BoardPage boardPage;

    /**
     * Инициализация домашней страницы.
     */
    @Step
    public static void initHomePage() {
        homePage = new HomePage();
    }

    /**
     * Открыть доску.
     *
     * @param boardName название доски
     */
    @Step
    public static void openBoard(String boardName) {
        homePage.openBoard(boardName);
    }

    /**
     * Инициализация страницы доски.
     */
    @Step
    public static void initBoardPage() {
        boardPage = new BoardPage();
    }

    /**
     * Открыть карточку.
     *
     * @param cardName the card name
     */
    @Step
    public static void openCard(String cardName) {
        boardPage.clickOnCard(cardName);
    }

    /**
     * Проверка карточки в колонке.
     *
     * @param expectedList the expected list name
     */
    @Step
    public static void checkCardOnList(String expectedList) {
        Assert.assertEquals(boardPage.getListName(), expectedList, "Карточка находится в другой колонке");
    }

    /**
     * Проверка выполнения чекбокса в контрольном списке
     *
     * @param checkboxName имя чекбокса
     */
    @Step
    public static void checklistCheckboxIsComplete(String checkboxName) {
        Assert.assertTrue(boardPage.checklistCheckboxIsComplete(checkboxName));
    }

    /**
     * Установить цвет обложки карточки.
     *
     * @param color цвет, на который изменить обложку
     */
    @Step
    public static void setCoverColor(CoverColors color) {
        boardPage.setCoverColor(color);
    }

    /**
     * Проверка цвета обложки карточки.
     *
     * @param color Ожидаемый цвет обложки карточки
     */
    @Step
    public static void coverColorSelected(CoverColors color) {
        Assert.assertTrue(boardPage.coverColorSelected(color), "Выбран другой цвет");
    }

    /**
     * Нажать на чекбокс выполнения карточки
     */
    @Step
    public static void clickOnDueCheckbox() {
        boardPage.clickOnDueCheckbox();
    }

    /**
     * Проверка, что карточка выполнена.
     */
    @Step
    public static void cardStatusIsComplete() {
        Assert.assertEquals(boardPage.getCardStatus(), "complete");
    }

    /**
     * Закрыть открытую карточку.
     */
    @Step
    public static void closeCard() {
        boardPage.closeCard();
    }

    /**
     * Установить зеленый фон для доски.
     */
    @Step
    public static void setGreenBackground() {
        boardPage.openBackgroundColors()
                .setBackgroundColor(BackgroundColors.GREEN);
    }

    /**
     * Проверка цвета фона доски.
     *
     * @param color ожидаемый цвет фона доски
     */
    @Step
    public static void checkBackgroundColor(BackgroundColors color) {
        Assert.assertTrue(boardPage.isBackgroundColor(color));
    }

    /**
     * Переименовать активную доску.
     *
     * @param name новое имя доски
     */
    @Step
    public static void renameBoard(String name) {
        boardPage.renameBoard(name);
    }

    /**
     * Проверка имени активной доски.
     *
     * @param name ожидаемое имя
     */
    @Step
    public static void checkBoardName(String name) {
        Assert.assertEquals(boardPage.getBoardName(), name);
    }

    /**
     * Закрыть активную доску.
     */
    @Step
    public static void closeActiveBoard(){
        boardPage.closeActiveBoard();
    }

    /**
     * Авторизация.
     */
    @Step
    public static void authorization(){
        inputCredential();
    }

    /**
     * Закрыть драйвер.
     */
    @Step
    public static void closeDriver(){
        driver.close();
        driver.quit();
    }

    /**
     * Создать веб драйвер.
     */
    @Step
    public static void createDriver(){
        driver = DriverManager.getDriver();
        BasePage.setDriver(driver);
    }

    /**
     * Ввести учетные данные.
     */
    @Step
    public static void inputCredential(){
        User user = UserUtils.initUser();
        mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickLogin();
        loginPage.authorization(user.getEmail(), user.getPassword());
    }

    /**
     * Перезагрузить страницу
     */
    @Step
    public static void refreshPage(){
        driver.navigate().refresh();
    }
}
