package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {
    private String url = "https://trello.com/u/insulinbaron/boards";

    @FindBys({@FindBy(xpath = "//div[@class='board-tile-details-name']")})
    List<WebElement> boards;
    public HomePage(){
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    private WebElement getBoard(String boardName){
        return boards.stream()
                .filter(WebElement::isDisplayed)
                .filter(we -> we.getAttribute("title").equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти доску \"" + boardName + "\""));
    }

    public BoardPage openBoard(String boardName){
        getBoard(boardName).click();
        return new BoardPage();
    }
}
