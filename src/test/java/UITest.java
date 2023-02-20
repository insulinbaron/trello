import pages.elements.colors.BackgroundColors;
import pages.elements.colors.CoverColors;
import org.testng.annotations.*;
import static steps.UISteps.*;


public class UITest {
    private String cardName = "Карточка для изучения API";

    @BeforeClass
    public void before(){
        createDriver();
        authorization();
        initHomePage();
        openBoard("kanbantool");
        initBoardPage();
    }

    @AfterClass
    public void after(){
        closeActiveBoard();
        closeDriver();
    }

    @BeforeMethod(onlyForGroups = "dependentOnCard")
    public void beforeCardGroup(){
        openCard(cardName);
    }

    @AfterMethod(onlyForGroups = "dependentOnCard")
    public void afterCardGroup(){
        closeCard();
    }

    @Test(testName = "Карточка в колонке",
    priority = 2,
    description = "Проверка отображения карточки в колонке",
    groups = "dependentOnCard")
    public void cardOnBoardTest() {
        checkCardOnList("Done");
    }

    @Test(testName = "Выполение чекбокса",
    description = "Проверка выполнения пунктов чекбоксов",
            groups = "dependentOnCard")
    public void completeCheckboxTest(){
        String firstCheckboxName = "Понять протокол HTTP";
        String secondCheckboxName = "Выучить методы запросов";
        checklistCheckboxIsComplete(firstCheckboxName);
        checklistCheckboxIsComplete(secondCheckboxName);
    }

    @Test(testName = "Установка обложки",
            description = "Проверка установки зеленой обложки для карточки",
            groups = "dependentOnCard")
    public void setCoverColorTest(){
        setCoverColor(CoverColors.GREEN);
        refreshPage();
        coverColorSelected(CoverColors.GREEN);
    }

    @Test(testName = "Выполенение задачи",
            description = "Проверка выполнения задания в срок",
            groups = "dependentOnCard")
    public void completeTaskTest(){
        clickOnDueCheckbox();
        cardStatusIsComplete();
    }

    @Test(testName = "Установка фона",
            description = "Проверка установка зеленого фона")
    public void setBackgroundColorTest(){
        setGreenBackground();
        checkBackgroundColor(BackgroundColors.GREEN);
    }

    @Test(testName = "Смена названия доски",
            description = "Проверка смены названия доски",
            dependsOnMethods = "setBackgroundColorTest")
    public void setNewBoardNameTest(){
        String newName = "Образование";
        renameBoard(newName);
        checkBoardName(newName);
    }
}
