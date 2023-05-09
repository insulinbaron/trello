package steps;

import api.requests.GetRequests;
import api.requests.PostRequest;
import api.requests.PutRequests;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import logger.Log;
import models.TrelloObject;
import org.testng.Assert;
import paths.JSONPaths;

import java.io.File;
import java.util.HashMap;

/**
 * The type Api steps.
 */
public class APISteps {

    /**
     * Создать трелло объект.
     *
     * @return the trello object
     */
    @Step
    public static TrelloObject createTrelloObject(){
        return new TrelloObject();
    }

    /**
     * Создать доску.
     *
     * @param params параметры для отправки запроса
     * @return the json path
     */
    @Step
    public static JsonPath createBoard(HashMap<String, String> params){
        Log.info("Создаем новую доску");
        JsonPath response = PostRequest.BoardRequests.createBoard(params);
        Log.info("Доска создана успешно");
        return response;
    }

    /**
     * Создать колонку.
     *
     * @param params параметры для отправки запроса
     * @return the json path
     */
    @Step
    public static JsonPath createList(HashMap<String, String> params){
        Log.info("Создаем новую колонку");
        JsonPath response = PostRequest.ListRequests.createNewList(params);
        Log.info("Колонка создана успешно");
        return response;
    }

    /**
     * Создать карточку.
     *
     * @param params параметры для отправки запроса
     * @return the json path
     */
    @Step
    public static JsonPath createCard(HashMap<String, String> params){
        Log.info("Создаем новую карточку");
        JsonPath response =  PostRequest.CardRequests.createCard(params);
        Log.info("Карточка создана успешно");
        return response;
    }

    /**
     * Прикрепление файла на карточку.
     *
     * @param pathKey   ключ параметра пути
     * @param pathValue значение параметра пути
     * @param image     прикрепляемый файл
     * @return the json path
     */
    @Step
    public static JsonPath createAttachment(String pathKey, String pathValue, File image){
        Log.info("Прикрепляем изображение на карточку");
        JsonPath response = PostRequest.CardRequests.createAttachmentOnCard(pathKey, pathValue, image);
        Log.info("Изображение прикреплено успешно");
        return response;
    }

    /**
     * Создать контрольный пункт.
     *
     * @param pathKey   ключ параметра пути
     * @param pathValue значение параметра пути
     * @param params    параметры запроса
     * @return the json path
     */
    @Step
    public static JsonPath createCheckItem(String pathKey, String pathValue, HashMap<String, String> params){
        Log.info("Создаем контрольный пункт");
        JsonPath response = PostRequest.CheckItemRequests.createCheckItem(pathKey, pathValue, params);
        Log.info("Контрольный пункт создан успешно");
        return response;
    }

    /**
     * Создать контролный список.
     *
     * @param pathKey   ключ параметра пути
     * @param pathValue значение параметра пути
     * @param params    параметры запроса
     * @return the json path
     */
    @Step
    public static JsonPath createCheckList(String pathKey, String pathValue, HashMap<String, String> params){
        Log.info("Создаем контрольный список");
        JsonPath response = PostRequest.CardRequests.createCheckListOnCard(pathKey, pathValue, params);
        Log.info("Контрольный список создан успешно");
        return response;
    }

    /**
     * Обновить карточку.
     *
     * @param pathKey   ключ параметра пути
     * @param pathValue значение параметра пути
     * @param params    параметры запроса
     * @return the json path
     */
    @Step
    public static JsonPath updateCard(String pathKey, String pathValue, HashMap<String, String> params){
        Log.info("Обновляем карточку");
        JsonPath response = PutRequests.CardRequests.updateCard(pathKey, pathValue, params);
        Log.info("Карточка обновлена успешно");
        return response;
    }

    /**
     * Обновление контрольного пункта.
     *
     * @param params     параметры пути
     * @param queryKey   ключ параметра запроса
     * @param queryValue значение параметра запроса
     * @return the json path
     */
    @Step
    public static JsonPath updateCheckItem(HashMap<String, String> params, String queryKey, String queryValue){
        Log.info("Обновление контрольного пунка");
        JsonPath response =  PutRequests.CardRequests.updateCheckItem(params, queryKey, queryValue);
        Log.info("Контрольный пункт обновлен успешно");
        return response;
    }


    /**
     * Получить карточку.
     *
     * @param pathKey   ключ параметра пути
     * @param pathValue значение параметра пути
     * @return the json path
     */
    @Step
    public static JsonPath getCard(String pathKey, String pathValue){
        Log.info("Получаем карточку");
        JsonPath response = GetRequests.CardRequests.getCard(pathKey, pathValue);
        Log.info("Карточка получена");
        return response;
    }

    /**
     * Проверка имени у трелло объекта.
     *
     * @param response     ответ в виде jsonpath
     * @param expectedName ожидаемое имя объекта
     */
    @Step
    public static void checkName(JsonPath response, String expectedName){
        Log.info("Проверка имени у объекта");
        Assert.assertEquals(response.getString(JSONPaths.NAME), expectedName, "Прозошла ошибка, имена не совпадают");
        Log.info("Проверка имени объекта прошла успешно");
    }

    /**
     * Проверка id доски у трелло объекта.
     *
     * @param response        ответ в виде jsonpath
     * @param expectedIdBoard ожидаемое id доски
     */
    @Step
    public static void checkIdBoard(JsonPath response, String expectedIdBoard){
        Log.info("Проверяем id доски у объекта");
        Assert.assertEquals(response.getString(JSONPaths.ID_BOARD), expectedIdBoard, "Произошла ошибка, объект находится на другой доске");
        Log.info("Проверка id у объекта прошла успешно");
    }

    /**
     * Проверка id колонки у трелло объекта
     *
     * @param response       ответ в виде jsonpath
     * @param expectedIdList ожидаемое id колонки
     */
    @Step
    public static void checkIdList(JsonPath response, String expectedIdList){
        Log.info("Проверяем id колонки у объекта");
        Assert.assertEquals(response.getString(JSONPaths.ID_LIST), expectedIdList, "Произошла ошибка, объект находится в другой колонке");
        Log.info("Проверка id колонки у объекта прошла успешно");
    }

    /**
     * Проверка загрузки файла.
     *
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void checkUpload(JsonPath response){
        Log.info("Проверяем загрузку файла");
        Assert.assertTrue(response.getBoolean(JSONPaths.IS_UPLOAD), "Прозошла ошибка, файл не загрузился");
        Log.info("Проверка загрузки файла прошла успешно");
    }

    /**
     * Проверка выполнения карточки.
     *
     * @param getResponse ответ get запроса в виде jsonpath
     * @param putResponse ответ put запроса в виде jsonpath
     */
    @Step
    public static void checkDueCard(JsonPath getResponse, JsonPath putResponse){
        Log.info("Проверяем выполнение карточки");
        Assert.assertEquals(getResponse.getString(JSONPaths.DUE), putResponse.getString(JSONPaths.DUE));
        Log.info("Проверка выполнения карточки прошла успешно");
    }

    /**
     * Проверка id карточки.
     *
     * @param response       ответ в виде jsonpath
     * @param expectedIdCard ожидаемое id карточки
     */
    @Step
    public static void checkIdCard(JsonPath response, String expectedIdCard){
        Log.info("Проверяем id карточки");
        Assert.assertEquals(response.getString(JSONPaths.ID_CARD), expectedIdCard, "Произошла ошибка, объект находится в другой карточке");
        Log.info("Проверка id карточки прошла успешно");
    }

    /**
     * Проверка id контрольного списка
     *
     * @param response            ответ в виде jsonpath
     * @param expectedIdCheckList ожидаемое id контрольного списка
     */
    @Step
    public static void checkIdCheckList(JsonPath response, String expectedIdCheckList){
        Log.info("Проверка id контрольного списка");
        Assert.assertEquals(response.getString(JSONPaths.ID_CHECKLIST), expectedIdCheckList, "При проверке id контрольного списка произошла ошибка");
        Log.info("Проверка id контрольного списка прошла успешно");
    }

    /**
     * Проверка состояния контрольного пункта
     *
     * @param response      ответ в виде jsonpath
     * @param expectedState ожидаемое состояние
     */
    @Step
    public static void checkCheckItemState(JsonPath response, String expectedState){
        Log.info("Проверка состояния контрольного пункта");
        Assert.assertEquals(response.getString(JSONPaths.STATE), expectedState, "При проверке состояния контрольного пункта произошла ошибка");
        Log.info("Проверка сосотояния контрольного пункта прошла успешно");
    }

    /**
     * Проверка id трелло объекта.
     *
     * @param response   ответ в виде jsonpath
     * @param expectedId ожидаемое id
     */
    @Step
    public static void checkId(JsonPath response, String expectedId){
        Log.info("Проверяем id объекта");
        Assert.assertEquals(response.getString(JSONPaths.ID), expectedId, "При проверке id произошла ошибка");
        Log.info("Проверка id объекта прошла успешно");
    }

    /**
     * Проверка закрытия.
     *
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void checkClosed(JsonPath response){
        Log.info("Проверяем закрытие объекта");
        Assert.assertTrue(response.getBoolean(JSONPaths.CLOSED));
        Log.info("Проверка закрытия объекта прошла успешно");
    }

    /**
     * Проверка изображения.
     *
     * @param response      ответ в виде jsonpath
     * @param expectedImage ожидаемое изображение
     */
    @Step
    public static void checkImage(JsonPath response, String expectedImage){
        Log.info("Проверяем изображение на объекте");
        Assert.assertEquals(response.getString(JSONPaths.IMAGE), expectedImage);
        Log.info("Проверка изображения на объекте прошла успешно");
    }


    /**
     * Переместить карточку.
     *
     * @param pathKey    ключ параметра пути
     * @param pathValue  значение параметра пути
     * @param queryKey   ключ параметра запроса
     * @param queryValue значение параметра запроса
     * @return the json path
     */
    @Step
    public static JsonPath transferCard(String pathKey, String pathValue, String queryKey, String queryValue){
        Log.info("Перемещаем карточку");
        JsonPath response = PutRequests.CardRequests.updateCard(pathKey, pathValue, queryKey, queryValue);
        Log.info("Карточка перемещена успешно");
        return response;
    }

    /**
     * Архивирование колонки.
     *
     * @param pathKey    ключ параметра пути
     * @param pathValue  значение параметра пути
     * @param queryKey   ключ параметра запроса
     * @param queryValue значение параметра запроса
     * @return the json path
     */
    @Step
    public static JsonPath columnArchiving(String pathKey, String pathValue, String queryKey, String queryValue){
        Log.info("Архивируем колонку");
        JsonPath response = PutRequests.ListRequests.updateList(pathKey, pathValue, queryKey, queryValue);
        Log.info("Архивация колонки выполнена успешно");
        return response;
    }

    /**
     * Добавление стикера на карточку.
     *
     * @param pathKey   ключ параметра пути
     * @param pathValue значение параметра пути
     * @param params    параметры запроса
     * @return the json path
     */
    @Step
    public static JsonPath addStickerOnCard(String pathKey, String pathValue, HashMap<String, String> params){
        Log.info("Добавление стикера на карточку");
        JsonPath response = PostRequest.CardRequests.addStickerOnCard(pathKey, pathValue, params);
        Log.info("Добавление стикера на карточку прошло успешно");
        return response;
    }

    /**
     * Проверка, что id не null.
     *
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void idNotNull(JsonPath response){
        Log.info("Проверяем id объекта на null");
        Assert.assertNotNull(response.getString(JSONPaths.ID));
        Log.info("Проверка id на null прошла успешно");
    }

    @Step
    public static TrelloObject.Board createBoard(JsonPath response){
        Log.info("Создаем доску");
        TrelloObject.Board board = new TrelloObject.Board(response);
        Log.info("Создана доска: " + board.toString());
        return board;
    }

    @Step
    public static TrelloObject.List createList(JsonPath response){
        Log.info("Создаем колонку");
        TrelloObject.List list = new TrelloObject.List(response);
        Log.info("Создана колонка: " + list.toString());
        return list;
    }

    @Step
    public static TrelloObject.Card createCard(JsonPath response){
        Log.info("Создаем карточку");
        TrelloObject.Card card = new TrelloObject.Card(response);
        Log.info("Создана карточка: " + card.toString());
        return card;
    }

    @Step
    public static TrelloObject.Attachment createAttachment(JsonPath response){
        Log.info("Создаем вложение");
        TrelloObject.Attachment attachment = new TrelloObject.Attachment(response);
        Log.info("Создана карточка: " + attachment.toString());
        return attachment;
    }


    @Step
    public static TrelloObject.CheckList createCheckList(JsonPath response){
        Log.info("Создаем чеклист");
        TrelloObject.CheckList checklist = new TrelloObject.CheckList(response);
        Log.info("Создан чеклист: " + checklist.toString());
        return checklist;
    }

    @Step
    public static TrelloObject.CheckItem createCheckItem(JsonPath response){
        Log.info("Создаем чекитем");
        TrelloObject.CheckItem checkitem = new TrelloObject.CheckItem(response);
        Log.info("Создан чекитем: " + checkitem.toString());
        return checkitem;
    }

}
