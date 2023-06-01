package steps;

import driver.DriverManager;
import io.qameta.allure.Step;
import logger.Log;
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
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UISteps.class.getName());
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
        Log.info("Открываем доску \"" + boardName + "\"");
        homePage.openBoard(boardName);
        Log.info("Доска \"" + boardName + "\" успешно открыта");
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
        Log.info("Открываем карточку: \"" + cardName + "\"");
        boardPage.clickOnCard(cardName);
        Log.info("Карточка \"" + cardName + "\" успешно открыта");
    }

    /**
     * Проверка карточки в колонке.
     *
     * @param expectedList the expected list name
     */
    @Step
    public static void checkCardOnList(String expectedList) {
        Log.info("Проверка карточки в колонке");
        Assert.assertEquals(boardPage.getListName(), expectedList, "Карточка находится в другой колонке");
        Log.info("Проверка карточки в колонке выполнена успешно. Картчока находится в колонце \"" + expectedList + "\"");
    }

    /**
     * Проверка выполнения чекбокса в контрольном списке
     *
     * @param checkboxName имя чекбокса
     */
    @Step
    public static void checklistCheckboxIsComplete(String checkboxName) {
        Log.info("Проверка выполения чекбокса");
        Assert.assertTrue(boardPage.checklistCheckboxIsComplete(checkboxName));
        Log.info("Проверка выполнения чекбокса выполнена успешно. Чекбокс \"" + checkboxName + "\" выполнен");
    }

    /**
     * Установить цвет обложки карточки.
     *
     * @param color цвет, на который изменить обложку
     */
    @Step
    public static void setCoverColor(CoverColors color) {
        Log.info("Устнавливаем цвет обложки \"" + color.getDescription() + "\"");
        boardPage.setCoverColor(color);
        Log.info("Установлен \"" + color.getDescription() + "\"");
    }

    /**
     * Проверка цвета обложки карточки.
     *
     * @param color Ожидаемый цвет обложки карточки
     */
    @Step
    public static void coverColorSelected(CoverColors color) {
        Log.info("Проверка установленого цвета обложки");
        Assert.assertTrue(boardPage.coverColorSelected(color), "Выбран другой цвет");
        Log.info("Проверка устновки цвета обложки выполнена успешно. Установлен цвет \"" + color.getDescription() + "\"");
    }

    /**
     * Нажать на чекбокс выполнения карточки
     */
    @Step
    public static void clickOnDueCheckbox() {
        Log.info("Выполняем клик по чекбоксу выполнения карточки");
        boardPage.clickOnDueCheckbox();
        Log.info("Чекбокс нажат");
    }

    /**
     * Проверка, что карточка выполнена.
     */
    @Step
    public static void cardStatusIsComplete() {
        Log.info("Проверка выполнения карточки");
        Assert.assertEquals(boardPage.getCardStatus(), "complete", "Карточка не выполнена");
        Log.info("Проверка выполнения карточки выполнена успешно");
    }

    /**
     * Закрыть открытую карточку.
     */
    @Step
    public static void closeCard() {
        Log.info("Закрываем открытую карточку");
        boardPage.closeCard();
        Log.info("Карточка закрыта");
    }

    /**
     * Установить зеленый фон для доски.
     */
    @Step
    public static void setGreenBackground() {
        Log.info("Устанавливаем зеленый цвет для фона доски");
        boardPage.openBackgroundColors()
                .setBackgroundColor(BackgroundColors.GREEN);
        Log.info("Зеленый цвет фона доска установлен успешно");
    }

    /**
     * Проверка цвета фона доски.
     *
     * @param color ожидаемый цвет фона доски
     */
    @Step
    public static void checkBackgroundColor(BackgroundColors color) {
        Log.info("Проверяем цвет фона доски");
        Assert.assertTrue(boardPage.isBackgroundColor(color));
        Log.info("Проверка цвета фона доски выполнена успешно. Установлен цвет \"" + color.getDescription() + "\"");
    }

    /**
     * Переименовать активную доску.
     *
     * @param name новое название доски
     */
    @Step
    public static void renameBoard(String name) {
        Log.info("Устанавливем новое название доски");
        boardPage.renameBoard(name);
        Log.info("Новое название доски установление успешно. Новое название \"" + name + "\"");
    }

    /**
     * Проверка имени активной доски.
     *
     * @param name ожидаемое имя
     */
    @Step
    public static void checkBoardName(String name) {
        Log.info("Проверка название активной доски");
        Assert.assertNotEquals(boardPage.getBoardName(), name, "Названия досок не совпадают");
        Log.info("Проверка называния доски прошло успешно. Название активной доски \"" + name + "\"");
    }

    /**
     * Закрыть активную доску.
     */
    @Step
    public static void closeActiveBoard(){
        Log.info("Закрываем активную доску");
        boardPage.closeActiveBoard();
        Log.info("Активная доска закрыта успешно");
    }

    /**
     * Авторизация.
     */
    @Step
    public static void authorization(){
        Log.info("Идет авторизация");
        inputCredential();
        Log.info("Авторизация выполнена успешно");
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
        Log.info("Вводим учетные данные");
        User user = UserUtils.initUser();
        mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickLogin();
        loginPage.authorization(user.getEmail(), user.getPassword());
        Log.info("Введены данные от УЗ \"" + user.getEmail() + "\"");
    }

    /**
     * Перезагрузить страницу
     */
    @Step
    public static void refreshPage(){
        Log.info("Перезагружаем текущую страницу");
        driver.navigate().refresh();
        Log.info("Страница перезагружена");
    }
}
