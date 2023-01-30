import api.requests.GetRequests;
import api.requests.PostRequest;
import api.requests.PutRequests;
import io.restassured.path.json.JsonPath;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class RestApiTest{
    private Board firstBoard;
    private ListOnBoard firstList;
    private Card firstCard;
    private Attachment firstAttachment;
    private CheckList firstCheckList;
    private CheckItem firstCheckItem;
    private CheckItem secondCheckItem;
    private ListOnBoard secondList;


    @BeforeClass
    public void before(){
        firstBoard = new Board();
        firstList = new ListOnBoard();
        firstCard = new Card();
        firstAttachment = new Attachment();
        firstCheckList = new CheckList();
        firstCheckItem = new CheckItem();
        secondCheckItem = new CheckItem();
        secondList = new ListOnBoard();
    }

    @Test( testName = "Проверка создания доски",
            priority = 1,
            dataProviderClass = DP.class,
            dataProvider = "setBoardQueryParams",
            description = "Создание новой доски")
    public void createBoardTest(HashMap<String, String> params){

        JsonPath response = PostRequest.BoardRequests.createBoard(params);

        firstBoard.setName(response.getString(Paths.NAME.getPath()));
        firstBoard.setId(response.getString(Paths.ID.getPath()));

        Assert.assertEquals(response.getString(Paths.NAME.getPath()), params.get(Paths.NAME.getPath()), "Прозошла ошибка при создании доски");
    }

    @Test(testName = "Проверка содания колонки",
            dataProviderClass = DP.class,
            dataProvider = "setListQueryParams",
            dependsOnMethods = "createBoardTest",
            description = "Создание новой колонки на доске")
    public void createListOnBoardTest(HashMap<String, String> params){
        params.put("idBoard", firstBoard.getId());

        JsonPath response = PostRequest.ListRequests.createNewList(params);

        Assert.assertEquals(response.getString(Paths.ID_BOARD.getPath()), firstBoard.getId(), "Колонка не создалась");
        Assert.assertEquals(response.getString(Paths.NAME.getPath()), params.get("name"), "произошла ошибка");

        firstList.setIdBoard(response.getString(Paths.ID_BOARD.getPath()));
        firstList.setId(response.getString(Paths.ID.getPath()));
        firstList.setName(response.getString(Paths.NAME.getPath()));
    }

    @Test(testName = "Проверка создания карточки",
            dataProviderClass = DP.class,
            dataProvider = "params",
            dependsOnMethods = "createListOnBoardTest",
            description = "Создание карточки в колонке")
    public void addCardOnListTest(HashMap<String, String> params){
        String name = "Карточка для изучения API";
        params.put("name", name);
        params.put("idList", firstList.getId());

        JsonPath response = PostRequest.CardRequests.createCard(params);

        Assert.assertEquals(response.getString(Paths.NAME.getPath()), params.get("name"));
        Assert.assertEquals(response.getString(Paths.ID_BOARD.getPath()), firstBoard.getId());
        Assert.assertEquals(response.getString(Paths.ID_LIST.getPath()), firstList.getId());

        firstCard.setId(response.getString(Paths.ID.getPath()));
        firstCard.setName(response.getString(Paths.NAME.getPath()));
        firstCard.setIdList(response.getString(Paths.ID_LIST.getPath()));
        firstCard.setIdBoard(response.getString(Paths.ID_BOARD.getPath()));
    }

    @Test(testName = "Проверка прикрепления изображения",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DP.class,
            dataProvider = "params",
            description = "Проверка прикрепления изображения на карточку")
    public void addAttachmentOnCardTest(HashMap<String, String> params) throws FileNotFoundException {
        File image = FileUtils.createFileImage();
        params.put("fields", "all");
        params.put("filter", "false");

        JsonPath response = PostRequest.CardRequests.createAttachmentOnCard("id", firstCard.getId(), image);

        Assert.assertTrue(response.getBoolean(Paths.IS_UPLOAD.getPath()), "Изображение не загрузилось");

        firstAttachment.setId(response.getString(Paths.ID.getPath()));
        firstAttachment.setName(response.getString(Paths.NAME.getPath()));

    }

    @Test(testName = "Проверка установка даты",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DP.class,
            dataProvider = "getNextDay",
            description = "Установка срока выполнения карточки на следующий день")
    public void setDeadlineTest(HashMap<String, String> queryParams){
        JsonPath putResponse = PutRequests.CardRequests.updateCard("id", firstCard.getId(), queryParams);
        JsonPath getResponse = GetRequests.CardRequests.getCard("id", firstCard.getId());

        Assert.assertEquals(getResponse.getString(Paths.DUE.getPath()), putResponse.getString(Paths.DUE.getPath()));
    }

    @Test(testName = "Изменение карточки",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DP.class,
            dataProvider = "setUpdateCardParams",
            description = "Добавление описания на карточке")
    public void setDescriptionOnCardTest(HashMap<String, String> queryParams){
        JsonPath putResponse = PutRequests.CardRequests.updateCard("id", firstCard.getId(), queryParams);
        JsonPath getResponse = GetRequests.CardRequests.getCard("id", firstCard.getId());

        Assert.assertEquals(getResponse.getString(Paths.DESC.getPath()), putResponse.getString(Paths.DESC.getPath()));
    }

    @Test(testName = "Создание чеклиста",
            description = "Проверка создания чеклиста на карточке",
            dependsOnMethods = "addCardOnListTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void createChecklistTest(HashMap<String, String> queryParams){
        queryParams.put("name", "API");

        JsonPath response = PostRequest.CardRequests.createCheckListOnCard("id", firstCard.getId(), queryParams);

        Assert.assertEquals(response.getString(Paths.NAME.getPath()), queryParams.get("name"));
        Assert.assertEquals(response.getString(Paths.ID_CARD.getPath()), firstCard.getId());
        Assert.assertEquals(response.getString(Paths.ID_BOARD.getPath()), firstBoard.getId());

        firstCheckList.setId(response.getString(Paths.ID.getPath()));
        firstCheckList.setName(response.getString(Paths.NAME.getPath()));
        firstCheckList.setIdBoard(response.getString(Paths.ID_BOARD.getPath()));
        firstCheckList.setIdCard(response.getString(Paths.ID_CARD.getPath()));
    }

    @Test( testName = "Создание чекитема",
            description = "Проверка создания дополнительных пунтков в чеклисте",
            dependsOnMethods = "createChecklistTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void createCheckItemOnChecklistTest(HashMap<String, String> params){
        params.put("name", "Понять протокол HTTP");

        JsonPath response = PostRequest.ChecklistRequests.createCheckItem("id", firstCheckList.getId(), params);

        Assert.assertEquals(response.getString(Paths.ID_CHECKLIST.getPath()), firstCheckList.getId());
        Assert.assertEquals(response.getString(Paths.NAME.getPath()), params.get("name"));

        firstCheckItem.setId(response.getString(Paths.ID.getPath()));
        firstCheckItem.setName(response.getString(Paths.NAME.getPath()));
        firstCheckItem.setIdChecklist(response.getString(Paths.ID_CHECKLIST.getPath()));
    }

    @Test(testName = "Создание дополнительного чекитема",
            description = "Проверка создания дополнительных пунтков в чеклисте",
            dependsOnMethods = "createChecklistTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void createCheckItemOnChecklistTest2(HashMap<String, String> params){
        params.put("name", "Выучить методы запросов");

        JsonPath response = PostRequest.ChecklistRequests.createCheckItem("id", firstCheckList.getId(), params);

        Assert.assertEquals(response.getString(Paths.ID_CHECKLIST.getPath()), firstCheckList.getId());
        Assert.assertEquals(response.getString(Paths.NAME.getPath()), params.get("name"));

        secondCheckItem.setId(response.getString(Paths.ID.getPath()));
        secondCheckItem.setName(response.getString(Paths.NAME.getPath()));
        secondCheckItem.setIdChecklist(response.getString(Paths.ID_CHECKLIST.getPath()));
    }

    @Test(testName = "Взаимодействие с чекитемом",
            description = "Изменение состояния контрольного пункта",
            dependsOnMethods = "createCheckItemOnChecklistTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void updateCheckItemOnCardTest(HashMap<String, String> params){
        params.put("id", firstCard.getId());
        params.put("idCheckItem", firstCheckItem.getId());

        JsonPath response = PutRequests.CardRequests.updateCheckItem(params, "state", "complete");

        Assert.assertEquals(response.getString(Paths.STATE.getPath()), "complete");
    }

    @Test(testName = "Создание колонки",
            description = "Создание дополнительной колонки",
            dependsOnMethods = "updateCheckItemOnCardTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void createOtherListOnBoardTest(HashMap<String, String> params){
        params.put("name", "Done");
        params.put("idBoard", firstBoard.getId());

        JsonPath response = PostRequest.ListRequests.createNewList(params);

        Assert.assertEquals(response.getString(Paths.ID_BOARD.getPath()), firstBoard.getId(), "Колонка не создалась");
        Assert.assertEquals(response.getString(Paths.NAME.getPath()), params.get("name"), "произошла ошибка");

        secondList.setIdBoard(response.getString(Paths.ID_BOARD.getPath()));
        secondList.setId(response.getString(Paths.ID.getPath()));
        secondList.setName(response.getString(Paths.NAME.getPath()));
    }

    @Test(testName = "Перенос карточки",
            description = "Проверка переноса карточки в другую колонку",
            dependsOnMethods = "createOtherListOnBoardTest")
    public void updateCardTest(){
        JsonPath response = PutRequests.CardRequests.updateCard("id", firstCard.getId(), "idList", secondList.getId());

        Assert.assertEquals(response.getString(Paths.ID_BOARD.getPath()), firstBoard.getId());
    }

    @Test(testName = "Архивирование колонки",
            description = "Проверка архивирования колонки",
            dependsOnMethods = "updateCardTest")
    public void archiveCardTest(){
       JsonPath response = PutRequests.ListRequests.updateList("id", firstList.getId(), "closed", "true");

       Assert.assertEquals(response.getString(Paths.ID.getPath()), firstList.getId());
       Assert.assertEquals(response.getString(Paths.NAME.getPath()), firstList.getName());
       Assert.assertTrue(response.getBoolean("closed"));
    }

    @Test(testName = "Взаимодействие с чекитемом",
            description = "Изменение состояния контрольного пункта",
            dependsOnMethods = "createCheckItemOnChecklistTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void updateSecondCheckItemOnCardTest(HashMap<String, String> params){
        params.put("id", firstCard.getId());
        params.put("idCheckItem", secondCheckItem.getId());

        JsonPath response = PutRequests.CardRequests.updateCheckItem(params, "state", "complete");

        Assert.assertEquals(response.getString(Paths.STATE.getPath()), "complete");
    }

    @Test(testName = "Прикрепление эмоджи",
            description = "Прикрепление эмоджи \"палец вверх\" на карточку",
            dependsOnMethods = "updateSecondCheckItemOnCardTest",
            dataProviderClass = DP.class,
            dataProvider = "params")
    public void addStickerTest(HashMap<String, String> params){
        params.put("image", "thumbsup");
        params.put("top", "0");
        params.put("left", "0");
        params.put("zIndex", "0");

        JsonPath response = PostRequest.CardRequests.addStickerOnCard("id", firstCard.getId(), params);

        Assert.assertNotNull(response.getString(Paths.ID.getPath()));
        Assert.assertEquals(response.getString(Paths.IMAGE.getPath()), params.get("image"));
    }

}
