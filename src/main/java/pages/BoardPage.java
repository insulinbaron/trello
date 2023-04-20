package pages;

import pages.elements.colors.BackgroundColors;
import pages.elements.colors.CoverColors;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.Logger;

import java.time.Duration;
import java.util.List;

/**
 * The type Board page.
 * Trello board page
 */
public class BoardPage extends BasePage {


    /**
     * Карточки на активной доске
     */
    @FindBys({@FindBy(xpath = "//span[contains(@class, 'list-card')]")})
    private List<WebElement> cards;

    /**
     * Имя колонки, на которой располагается активная карточка
     */
    @FindBy(xpath = "//a[contains(@class, 'js-open-move')]")
    private WebElement listName;

    /**
     *  Чекбоксы контрольного списка
     */
    @FindBys({@FindBy(xpath = "//div[contains(@class, 'checklist-item') and contains(@class, 'no-assignee')]")})
    private List<WebElement> checkListCheckboxes;

    /**
     *  Чекбокс выполнения карточки в срок
     */
    @FindBy(xpath = "//a[contains(@aria-label, 'due')]")
    private WebElement dueDateCheckbox;

    /**
     *  Кнопка для изменения обложки карточки
     */
    @FindBys({@FindBy(xpath = "//*[normalize-space(text())='Cover']")})
    private List<WebElement> coverButtons;


    /**
     * Статус выполнения карточки
     */
    @FindBy(xpath = "//div[contains(@class, 'card-detail-due-date-badge')]")
    private WebElement cardDueStatus;

    /**
     * Кнопка закрытия активной карточки.
     */
    @FindBy(xpath = "//a[contains(@class, 'dialog-close-button')]")
    private WebElement closeCardButton;

    /**
     * Кнопка открытия меню на активной трелло доске
     */
    @FindBy(xpath = "//button[@aria-label='Show menu']")
    private WebElement menuButton;

    /**
     * Кнопка в меню доски для изменения фона
     */
    @FindBy(xpath = "//a[contains(@class, 'change-background')]")
    private WebElement changeBackgroundButton;

    /**
     * Кнопка в меню доски, после нажатия отображаются доступные цвета для смены фона доски трелло
     */
    @FindBy(xpath = "//div[contains(text(), 'Colors')]")
    private WebElement backgroundColorsButton;

    /**
     * Доступные цвета для изменения фона доски трелло
     */
    @FindBys({@FindBy(xpath = "//div[contains(@style, 'background-color') and contains(@class, 'image')]")})
    private List<WebElement> backgroundColors;

    /**
     * Название активной доски
     */
    @FindBy(xpath = "//div[contains(@class, 'board-name')]")
    private WebElement boardName;

    /**
     * Кнопка закрытия меню доски
     */
    @FindBy(xpath = "//a[contains(@class, 'menu-header-close-button')]")
    private WebElement menuCloseButton;

    /**
     * Корневой вебэлемент страницы
     */
    @FindBy(xpath = "//div[@id='trello-root']")
    private WebElement root;

    /**
     * Кнопка 'more' в меню доски.
     */
    @FindBy(xpath = "//a[contains(@class, 'open-more')]")
    private WebElement moreButton;

    /**
     *  Кнопка закрытия доски в меню доски
     */
    @FindBy(xpath = "//a[contains(@class, 'close-board')]")
    private WebElement closeBoardButton;

    /**
     * Всплывающее окно подтверждения закртия доски
     */
    @FindBy(xpath = "//div[@class='no-back']")
    private WebElement noBackPopup;

    /**
     * Кнопка для подтверждения закртия доски в всплывающем окне подтверждения
     */
    @FindBy(xpath = "//input[@value='Close']")
    private WebElement closeSubmitButton;

    /**
     * Всплывающее окно подтверждения удаления доски
     */
    @FindBy(xpath = "//h1[contains(@data-testid, 'close-board')]")
    private WebElement closeBoardPopup;

    /**
     * кнопка для окончательного удаления доски.
     * после нажатия появляется всплывающее окно, в котором требуется подтвердить текущее действие
     */
    @FindBy(xpath = "//button[normalize-space(text())='Permanently delete board']")
    private WebElement permanentlyDelete;

    /**
     * Кнопка удаления доски
     */
    @FindBy(xpath = "//button[normalize-space(text())='Delete']")
    private WebElement deleteButton;

    /**
     * Кнопка "назад" для навигации в меню доски
     */
    @FindBy(xpath = "//a[contains(@class, 'icon-back')]")
    private WebElement backMenuButton;

    /**
     * Цвета обложки карточки, доступные для выбора
     */
    @FindBys({@FindBy(xpath = "//button[contains(@class, 'mQ')]")})
    private List<WebElement> colors;

    /**
     * Обложка карточки
     */
    @FindBy(xpath = "//div[contains(@class, 'window-cover') and parent::div[contains(@class, 'card-detail-window')]]")
    private WebElement windowCover;


    /**
     * Instantiates a new Board page.
     */
    public BoardPage(){
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl);
        PageFactory.initElements(driver, this);
    }

    /**
     * Возвращает карточку трелло по имени
     * cardName - имя карточки трелло
     * @return WebElement trello card
     */
    private WebElement getCard(String cardName){
        Logger.log("Проверяем наличие карточки " + cardName);
        WebElement element = cards.stream()
                .filter(WebElement::isDisplayed)
                .filter(card -> card.getText().equalsIgnoreCase(cardName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти карточку \"" + cardName + "\""));
        Logger.log("Карточка " + cardName + " найдена");
        return element;
    }

    /**
     * Открыть карточку
     *
     * @param cardName имя карточки, которую нужно открыть
     * @return the board page
     */
    public BoardPage clickOnCard(String cardName){
        Logger.log("Выполняется открытие карточки " + cardName);
        getCard(cardName).click();
        Logger.log("Карточка " + cardName + " открыта");
        return this;
    }

    /**
     * Get list name string.
     *
     * @return имя колонки
     */
    public String getListName(){
        return listName.getText();
    }

    private WebElement getCheckListCheckbox(String checkboxName){
        Logger.log("Производится поиск чекбокса \"" + checkboxName + "\"");
        WebElement checkbox = checkListCheckboxes.stream()
                .filter(WebElement::isDisplayed)
                .filter(cb -> cb.getText().equalsIgnoreCase(checkboxName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти чекбокс \"" + checkboxName + "\""));
        Logger.log("Чекбокс \"" + checkboxName + "\" найден");
        return checkbox;
    }

    /**
     * Проверяет выполнен ли чекбокс в контрольном списке.
     *
     * @param checkboxName название чекбокса на контрольном списке
     * @return the boolean
     * true если чекбокс выполнен и false если не выполнен
     */
    public boolean checklistCheckboxIsComplete(String checkboxName){
        String value = getCheckListCheckbox(checkboxName).getAttribute("class");
        return value.contains("complete");
    }


    /**
     * Нажать на чекбокс выполнения карточки.
     */
    public void clickOnDueCheckbox(){
        dueDateCheckbox.click();
    }


    /**
     * Устанавливает цвет обложки на карточке.
     *
     * @param color цвет который стоит установить в качестве обложки карточки
     * @return the board page
     */
    public BoardPage setCoverColor(CoverColors color){
        WebElement button = coverButtons.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Кнопка не отображается"));
        button.click();
        getCoverColor(color).click();
        return this;
    }



    /**
     *
     * Проверяет, что выбран нужный цвет карточки
     *
     * @param color проверяемый цвет
     * @return the boolean
     * true если выбран ожидаемый цвет и false если выбран другой цвет
     */
    public boolean coverColorSelected(CoverColors color){
        boolean result = false;
        try {
            result = windowCover.getCssValue("background-color").equalsIgnoreCase(color.getRgba()) ? true : false;
        } catch (Exception e){

        }
        return result;
    }


    /**
     * Проверяет какой статус у карточки.
     *
     * @return статус карточки
     */
    public String getCardStatus(){
        String title = cardDueStatus.getAttribute("title");
        String result = null;
        switch (title){
            case ("This card is complete."):
                result = "complete";
                break;
            case ("This card is due later."):
                result = "due later";
                break;
            case ("This card is past due."):
                result = "past due";
                break;
        }
        return result;
    }

    /**
     * Закрывает активную карточку.
     *
     * @return the board page
     */
    public BoardPage closeCard(){ closeCardButton.click();
        return this;}


    /**
     * Возвращает веб элемент цвета фона доски
     *
     * @param color цвет, веб элемент которого нужно вернуть
     * @return веб элемент цвета
     */

    private WebElement getBackgroundColorButton(BackgroundColors color){
        return backgroundColors.stream()
                .filter(WebElement::isDisplayed)
                .filter(button -> button.getAttribute("style").contains(color.getRgb()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти кнопку с rgb: \"" + color.getRgb() + "\""));
    }

    /**
     * Установка фона доски.
     *
     * @param color цвет который нужно установить
     * @return the board page
     */
    public BoardPage setBackgroundColor(BackgroundColors color){
        getBackgroundColorButton(color).click();
        backMenuButton.click();
        backMenuButton.click();
        menuCloseButton.click();
        return this;
    }

    /**
     * Открывает вкладку в меню доски, на которой отображаются цвета для выбора фона доски.
     *
     * @return the board page
     */
    public BoardPage openBackgroundColors(){
        menuButton.click();
        changeBackgroundButton.click();
        backgroundColorsButton.click();
        return this;
    }

    /**
     * Переименовывает активную доску.
     *
     * @param newName новое имя доски
     * @return the board page
     */
    public BoardPage renameBoard(String newName){
        Actions action = new Actions(driver);
        action.doubleClick(boardName)
                .pause(Duration.ofSeconds(1))
                .sendKeys(newName)
                .pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        return this;
    }

    /**
     * Проверяет цвет, который выбран в качестве фона доски
     *
     * @param color ожидаемый цвет фона доски
     * @return the boolean
     * true если выбран ожидаемый цвет и false если выбран любой другой цвет
     */
    public boolean isBackgroundColor(BackgroundColors color){
        String style = root.getAttribute("style");
        return style.contains(color.getRgb());
    }

    /**
     * Возвращает имя активной доски.
     *
     * @return the string name of board
     */
    public String getBoardName(){
        return boardName.getText();
    }

    /**
     * Закрывает активную доску.
     */
    public void closeActiveBoard(){
        menuButton.click();
        moreButton.click();
        closeBoardButton.click();
        if(noBackPopup.isDisplayed()){
           closeSubmitButton.click();
        }
        if(closeBoardPopup.isDisplayed()){
            permanentlyDelete.click();
            deleteButton.click();
        }
    }
    /**
     * Is background color boolean.
     *
     * @param coverColor the color
     * @return the boolean
     * true if desired color selected else false
     */

    private WebElement getCoverColor(CoverColors coverColor){
        return colors.stream()
                .filter(WebElement::isDisplayed)
                .filter(color -> color.getCssValue("background-color").equalsIgnoreCase(coverColor.getRgba()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Цвет не найден"));

    }
}
