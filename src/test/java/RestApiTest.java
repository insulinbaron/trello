import io.restassured.path.json.JsonPath;
import models.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import providers.DataProvider;
import utils.FileUtils;
import static steps.APISteps.*;

import java.io.File;
import java.util.HashMap;

public class RestApiTest{
    private TrelloObject board;
    private TrelloObject firstList;
    private TrelloObject firstCard;
    private TrelloObject attachment;
    private TrelloObject checklist;
    private TrelloObject firstCheckItem;
    private TrelloObject secondCheckItem;
    private TrelloObject secondList;


    @BeforeClass
    public void before(){
        board = createTrelloObject();
        firstList = createTrelloObject();
        firstCard = createTrelloObject();
        attachment = createTrelloObject();
        checklist = createTrelloObject();
        firstCheckItem = createTrelloObject();
        secondCheckItem = createTrelloObject();
        secondList = createTrelloObject();
    }

    @Test( testName = "Проверка создания доски",
            priority = 1,
            dataProviderClass = DataProvider.class,
            dataProvider = "boardQueryParams",
            description = "Создание новой доски")
    public void createBoardTest(HashMap<String, String> params){
        JsonPath response = createBoard(params);

        checkName(response, params.get("name"));

        setName(board, response);
        setId(board, response);
    }

    @Test(testName = "Проверка содания колонки",
            dataProviderClass = DataProvider.class,
            dataProvider = "setListQueryParams",
            dependsOnMethods = "createBoardTest",
            description = "Создание новой колонки на доске")
    public void createListOnBoardTest(HashMap<String, String> params){
        params.put("idBoard", board.getId());

        JsonPath response = createList(params);

        checkIdBoard(response, board.getId());
        checkName(response, params.get("name"));

        setIdBoard(firstList, response);
        setId(firstList, response);
        setName(firstList, response);
    }

    @Test(testName = "Проверка создания карточки",
            dataProviderClass = DataProvider.class,
            dataProvider = "params",
            dependsOnMethods = "createListOnBoardTest",
            description = "Создание карточки в колонке")
    public void addCardOnListTest(HashMap<String, String> params){
        String name = "Карточка для изучения API";
        params.put("name", name);
        params.put("idList", firstList.getId());

        JsonPath response = createCard(params);

        checkName(response, params.get("name"));
        checkIdBoard(response, board.getId());
        checkIdList(response, firstList.getId());

        setId(firstCard, response);
        setName(firstCard, response);
        setIdList(firstCard, response);
        setIdBoard(firstCard, response);

    }

    @Test(testName = "Проверка прикрепления изображения",
            dependsOnMethods = "addCardOnListTest",
            description = "Проверка прикрепления изображения на карточку")
    public void addAttachmentOnCardTest(){
        File image = FileUtils.createFileImage();

        JsonPath response = createAttachment("id", firstCard.getId(), image);

        checkUpload(response);

        setId(attachment, response);
        setName(attachment, response);
    }

    @Test(testName = "Проверка установка даты",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "getNextDay",
            description = "Установка срока выполнения карточки на следующий день")
    public void setDeadlineTest(HashMap<String, String> params) {
        JsonPath putResponse = updateCard("id", firstCard.getId(), params);
        JsonPath getResponse = getCard("id", firstCard.getId());

        checkDueCard(getResponse, putResponse);
    }

    @Test(testName = "Изменение карточки",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "setUpdateCardParams",
            description = "Добавление описания на карточке")
    public void setDescriptionOnCardTest(HashMap<String, String> queryParams) {
        JsonPath putResponse = updateCard("id", firstCard.getId(), queryParams);
        JsonPath getResponse = getCard("id", firstCard.getId());

        checkDueCard(getResponse, putResponse);
    }

    @Test(testName = "Создание чеклиста",
            description = "Проверка создания чеклиста на карточке",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void createChecklistTest(HashMap<String, String> params) {
        params.put("name", "API");

        JsonPath response = createCheckList("id", firstCard.getId(), params);

        checkName(response, params.get("name"));
        checkIdCard(response, firstCard.getId());
        checkIdBoard(response, board.getId());

        setId(checklist, response);
        setName(checklist, response);
        setIdBoard(checklist, response);
        setIdCard(checklist, response);
    }

    @Test(testName = "Создание чекитема",
            description = "Проверка создания дополнительных пунтков в чеклисте",
            dependsOnMethods = "createChecklistTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void createCheckItemOnChecklistTest(HashMap<String, String> params) {
        params.put("name", "Понять протокол HTTP");

        JsonPath response = createCheckItem("id", checklist.getId(), params);

        checkIdCheckList(response, checklist.getId());
        checkName(response, params.get("name"));

        setId(firstCheckItem, response);
        setName(firstCheckItem, response);
        setIdCheckList(firstCheckItem, response);
    }

    @Test(testName = "Создание дополнительного чекитема",
            description = "Проверка создания дополнительных пунтков в чеклисте",
            dependsOnMethods = "createChecklistTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void createCheckItemOnChecklistTest2(HashMap<String, String> params) {
        params.put("name", "Выучить методы запросов");

        JsonPath response = createCheckItem("id", checklist.getId(), params);


        checkIdCheckList(response, checklist.getId());
        checkName(response, params.get("name"));

        setId(secondCheckItem, response);
        setName(secondCheckItem, response);
        setIdCheckList(secondCheckItem, response);
    }

    @Test(testName = "Взаимодействие с чекитемом",
            description = "Изменение состояния контрольного пункта",
            dependsOnMethods = "createCheckItemOnChecklistTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void updateCheckItemOnCardTest(HashMap<String, String> params) {
        params.put("id", firstCard.getId());
        params.put("idCheckItem", firstCheckItem.getId());

        JsonPath response = updateCheckItem(params, "state", "complete");

        checkCheckItemState(response, "complete");
    }

    @Test(testName = "Создание колонки",
            description = "Создание дополнительной колонки",
            dependsOnMethods = "updateCheckItemOnCardTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void createOtherListOnBoardTest(HashMap<String, String> params) {
        params.put("name", "Done");
        params.put("idBoard", board.getId());

        JsonPath response = createList(params);

        checkIdBoard(response, board.getId());
        checkName(response, params.get("name"));

        setIdBoard(secondList, response);
        setId(secondList, response);
        setName(secondList, response);
    }

    @Test(testName = "Перенос карточки",
            description = "Проверка переноса карточки в другую колонку",
            dependsOnMethods = "createOtherListOnBoardTest")
    public void updateCardTest() {
        JsonPath response = transferCard("id", firstCard.getId(), "idList", secondList.getId());

        checkIdBoard(response, board.getId());
    }

    @Test(testName = "Архивирование колонки",
            description = "Проверка архивирования колонки",
            dependsOnMethods = "updateCardTest")
    public void archiveCardTest(){

        JsonPath response = columnArchiving("id", firstList.getId(), "closed", "true");


        checkId(response, firstList.getId());
        checkName(response, firstList.getName());
        checkClosed(response);
    }

    @Test(testName = "Взаимодействие с чекитемом",
            description = "Изменение состояния контрольного пункта",
            dependsOnMethods = "createCheckItemOnChecklistTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void updateSecondCheckItemOnCardTest(HashMap<String, String> params){
        params.put("id", firstCard.getId());
        params.put("idCheckItem", secondCheckItem.getId());

        JsonPath response = updateCheckItem(params, "state", "complete");

        checkCheckItemState(response, "complete");
    }

    @Test(testName = "Прикрепление эмоджи",
            description = "Прикрепление эмоджи \"палец вверх\" на карточку",
            dependsOnMethods = "updateSecondCheckItemOnCardTest",
            dataProviderClass = DataProvider.class,
            dataProvider = "params")
    public void addStickerTest(HashMap<String, String> params){
        params.put("image", "thumbsup");
        params.put("top", "0");
        params.put("left", "0");
        params.put("zIndex", "0");

        JsonPath response = addStickerOnCard("id", firstCard.getId(), params);

        idNotNull(response);
        checkImage(response, params.get("image"));
    }

}
