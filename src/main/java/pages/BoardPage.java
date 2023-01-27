package pages;


import org.openqa.selenium.By;
import pages.elements.colors.BackgroundColors;
import pages.elements.colors.CoverColors;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class BoardPage extends BasePage {


    @FindBys({@FindBy(xpath = "//div[@class='list js-list-content']")})
    List<WebElement> lists;

    @FindBys({@FindBy(xpath = "//span[contains(@class, 'list-card')]")})
    List<WebElement> cards;

    @FindBy(xpath = "//a[contains(@class, 'js-open-move')]")
    WebElement listName;

    @FindBys({@FindBy(xpath = "//div[contains(@class, 'checklist-item') and contains(@class, 'no-assignee')]")})
    List<WebElement> checkListCheckboxes;

    @FindBy(xpath = "//a[contains(@aria-label, 'due')]")
    WebElement dueDateCheckbox;

    @FindBy(xpath = "//a[contains(@class, 'window-cover-menu-button')]")
    WebElement cardCoverButton;

    @FindBy(xpath = "//button[contains(@class,'aRrCrZDsIPXOIa kx4oqRlqCFjWrx')]")
    WebElement greenCoverButton;

    @FindBy(xpath = "//span[@class='QGQQqF2k7sDfTH PIt9GK-OWhTljI']")
    WebElement cardStatus;

    @FindBy(xpath = "//a[contains(@class, 'dialog-close-button')]")
    WebElement closeCardButton;

    @FindBy(xpath = "//button[@aria-label='Show menu']")
    WebElement menuButton;

    @FindBy(xpath = "//a[contains(@class, 'change-background')]")
    WebElement changeBackgroundButton;

    @FindBy(xpath = "//div[contains(text(), 'Colors')]")
    WebElement backgroundColorsButton;

    @FindBys({@FindBy(xpath = "//div[contains(@style, 'background-color') and contains(@class, 'image')]")})
    List<WebElement> backgroundColors;

    @FindBy(xpath = "//div[contains(@class, 'board-name')]")
    WebElement boardName;

    @FindBy(xpath = "//a[contains(@class, 'menu-header-close-button')]")
    WebElement menuCloseButton;

    @FindBy(xpath = "//button[contains(@class, 'aRrCrZDsIPXOIa')]")
    List<WebElement> coverColors;

    @FindBy(xpath = "//div[@id='trello-root']")
    WebElement root;

    @FindBy(xpath = "//a[contains(@class, 'open-more')]")
    WebElement moreButton;

    @FindBy(xpath = "//a[contains(@class, 'close-board')]")
    WebElement closeBoardButton;

    @FindBy(xpath = "//div[@class='no-back']")
    WebElement noBackPopup;

    @FindBy(xpath = "//input[@value='Close']")
    WebElement closeSubmitButton;

    @FindBy(xpath = "//h1[contains(@data-testid, 'close-board')]")
    WebElement closeBoardPopup;

    @FindBy(xpath = "//button[normalize-space(text())='Permanently delete board']")
    WebElement permanentlyDelete;

    @FindBy(xpath = "//button[normalize-space(text())='Delete']")
    WebElement deleteButton;

    @FindBy(xpath = "//a[contains(@class, 'icon-back')]")
    WebElement backMenuButton;


    public BoardPage(){
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl);
        PageFactory.initElements(driver, this);
    }

    private WebElement getCard(String cardName){
        return cards.stream()
                .filter(WebElement::isDisplayed)
                .filter(card -> card.getText().equalsIgnoreCase(cardName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти карточку \"" + cardName + "\""));
    }

    public BoardPage clickOnCard(String cardName){
        getCard(cardName).click();
        return this;
    }

    public String getListName(){
        return listName.getText();
    }

    private WebElement getCheckListCheckbox(String checkboxName){
        return checkListCheckboxes.stream()
                .filter(WebElement::isDisplayed)
                .filter(cb -> cb.getText().equalsIgnoreCase(checkboxName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти чекбокс \"" + checkboxName + "\""));
    }

    public boolean checklistCheckboxIsComplete(String checkboxName){
        String value = getCheckListCheckbox(checkboxName).getAttribute("class");
        return value.contains("complete");
    }


    public void clickOnDueCheckbox(){
        dueDateCheckbox.click();
    }

    public BoardPage setGreenCover(){
        cardCoverButton.click();
        greenCoverButton.click();
        return this;
    }
    public BoardPage setCoverColor(CoverColors color){
        cardCoverButton.click();
        getCoverColor(color).click();
        return this;
    }

    private WebElement getCoverColor(CoverColors color) {
        return coverColors.stream()
                .filter(WebElement::isDisplayed)
                .filter(el -> el.getAttribute("class").contains(color.getCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Цвет не найден"));
    }

    public boolean coverColorSelected(CoverColors color){
        WebElement we = null;
        boolean result = false;
        switch (color) {
            case GREEN:
                we = getCoverColor(CoverColors.GREEN);
                break;
        }
        try {
            result = we.getAttribute("class").contains("_3GFiyhGr6WTMLB") ? true : false;
        } catch (Exception e){

        }
        return result;
    }



    public String getCardStatus(){
        return cardStatus.getText().trim();
    }

    public BoardPage closeCard(){ closeCardButton.click();
        return this;}

    public WebElement getBackgroundColor(BackgroundColors color){
        WebElement webElement = null;
        switch (color) {
            case GREEN:
                webElement = getBackgroundButton(BackgroundColors.GREEN.getRgb());
                break;
        }
        return  webElement;
    }

    private WebElement getBackgroundButton(String rgb){
        return backgroundColors.stream()
                .filter(WebElement::isDisplayed)
                .filter(button -> button.getAttribute("style").contains(rgb))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти кнопку с rgb: \"" + rgb + "\""));
    }

    public BoardPage setBackgroundColor(BackgroundColors color){
        getBackgroundColor(color).click();
        backMenuButton.click();
        backMenuButton.click();
        menuCloseButton.click();
        return this;
    }

    public BoardPage openBackgroundColors(){
        menuButton.click();
        changeBackgroundButton.click();
        backgroundColorsButton.click();
        return this;
    }

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

    public boolean isBackgroundColor(BackgroundColors color){
        String style = root.getAttribute("style");
        return style.contains(color.getRgb());
    }
    public String getBoardName(){
        return boardName.getText();
    }

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
}
