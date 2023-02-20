package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * The type Home page.
 */
public class HomePage extends BasePage {
    private String url = "https://trello.com/u/insulinbaron/boards";

    /**
     *
     * Созданные доски
     */
    @FindBys({@FindBy(xpath = "//div[@class='board-tile-details-name']")})
    List<WebElement> boards;

    /**
     * Instantiates a new Home page.
     */
    public HomePage(){
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    /**
     * Возвращает веб элемент доску трелло.
     *
     * @param boardName имя трелло доски
     * @return board
     */

    private WebElement getBoard(String boardName){
        return boards.stream()
                .filter(WebElement::isDisplayed)
                .filter(we -> we.getAttribute("title").equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти доску \"" + boardName + "\""));
    }

    /**
     * Открыть доску.
     *
     * @param boardName имя доски, которую нужно открыть
     */
    public void openBoard(String boardName){
        getBoard(boardName).click();
    }
}
