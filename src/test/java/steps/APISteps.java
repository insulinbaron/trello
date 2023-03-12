package steps;

import api.requests.GetRequests;
import api.requests.PostRequest;
import api.requests.PutRequests;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
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
        return PostRequest.BoardRequests.createBoard(params);
    }

    /**
     * Создать доску.
     *
     * @param params параметры для отправки запроса
     * @return the json path
     */
    @Step
    public static JsonPath createList(HashMap<String, String> params){
        return PostRequest.ListRequests.createNewList(params);
    }

    /**
     * Создать карточку.
     *
     * @param params параметры для отправки запроса
     * @return the json path
     */
    @Step
    public static JsonPath createCard(HashMap<String, String> params){
        return PostRequest.CardRequests.createCard(params);
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
        return PostRequest.CardRequests.createAttachmentOnCard(pathKey, pathValue, image);
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
        return PostRequest.CheckItemRequests.createCheckItem(pathKey, pathValue, params);
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
        return PostRequest.CardRequests.createCheckListOnCard(pathKey, pathValue, params);
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
        return PutRequests.CardRequests.updateCard(pathKey, pathValue, params);
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
        return PutRequests.CardRequests.updateCheckItem(params, queryKey, queryValue);
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
        return GetRequests.CardRequests.getCard(pathKey, pathValue);
    }

    /**
     * Проверка имени у трелло объекта.
     *
     * @param response     ответ в виде jsonpath
     * @param expectedName ожидаемое имя объекта
     */
    @Step
    public static void checkName(JsonPath response, String expectedName){
        Assert.assertEquals(response.getString(JSONPaths.NAME), expectedName, "Прозошла ошибка, имена не совпадают");
    }

    /**
     * Проверка id доски у трелло объекта.
     *
     * @param response        ответ в виде jsonpath
     * @param expectedIdBoard ожидаемое id доски
     */
    @Step
    public static void checkIdBoard(JsonPath response, String expectedIdBoard){
        Assert.assertEquals(response.getString(JSONPaths.ID_BOARD), expectedIdBoard, "Произошла ошибка, объект находится на другой доске");
    }

    /**
     * Проверка id колонки у трелло объекта
     *
     * @param response       ответ в виде jsonpath
     * @param expectedIdList ожидаемое id колонки
     */
    @Step
    public static void checkIdList(JsonPath response, String expectedIdList){
        Assert.assertEquals(response.getString(JSONPaths.ID_LIST), expectedIdList, "Произошла ошибка, объект находится в другой колонке");
    }

    /**
     * Проверка загрузки файла.
     *
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void checkUpload(JsonPath response){
        Assert.assertTrue(response.getBoolean(JSONPaths.IS_UPLOAD), "Прозошла ошибка, файл не загрузился");
    }

    /**
     * Проверка выполнения карточки.
     *
     * @param getResponse ответ get запроса в виде jsonpath
     * @param putResponse ответ put запроса в виде jsonpath
     */
    @Step
    public static void checkDueCard(JsonPath getResponse, JsonPath putResponse){
        Assert.assertEquals(getResponse.getString(JSONPaths.DUE), putResponse.getString(JSONPaths.DUE));
    }

    /**
     * Проверка id карточки.
     *
     * @param response       ответ в виде jsonpath
     * @param expectedIdCard ожидаемое id карточки
     */
    @Step
    public static void checkIdCard(JsonPath response, String expectedIdCard){
        Assert.assertEquals(response.getString(JSONPaths.ID_CARD), expectedIdCard, "Произошла ошибка, объект находится в другой карточке");
    }

    /**
     * Проверка id контрольного списка
     *
     * @param response            ответ в виде jsonpath
     * @param expectedIdCheckList ожидаемое id контрольного списка
     */
    @Step
    public static void checkIdCheckList(JsonPath response, String expectedIdCheckList){
        Assert.assertEquals(response.getString(JSONPaths.ID_CHECKLIST), expectedIdCheckList);
    }

    /**
     * Проверка состояния контрольного пункта
     *
     * @param response      ответ в виде jsonpath
     * @param expectedState ожидаемое состояние
     */
    @Step
    public static void checkCheckItemState(JsonPath response, String expectedState){
        Assert.assertEquals(response.getString(JSONPaths.STATE), expectedState);
    }

    /**
     * Проверка id трелло объекта.
     *
     * @param response   ответ в виде jsonpath
     * @param expectedId ожидаемое id
     */
    @Step
    public static void checkId(JsonPath response, String expectedId){
        Assert.assertEquals(response.getString(JSONPaths.ID), expectedId);
    }

    /**
     * Проверка закрытия.
     *
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void checkClosed(JsonPath response){
        Assert.assertTrue(response.getBoolean(JSONPaths.CLOSED));
    }

    /**
     * Проверка изображения.
     *
     * @param response      ответ в виде jsonpath
     * @param expectedImage ожидаемое изображение
     */
    @Step
    public static void checkImage(JsonPath response, String expectedImage){
        Assert.assertEquals(response.getString(JSONPaths.IMAGE), expectedImage);
    }

    /**
     * Установить имя для трелло объекта.
     *
     * @param object   трелло объект
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void setName(TrelloObject object, JsonPath response){
        object.setName(response.getString(JSONPaths.NAME));
    }

    /**
     * Установить id для трелло объекта.
     *
     * @param object   трелло объект
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void setId(TrelloObject object, JsonPath response){
        object.setId(response.getString(JSONPaths.ID));
    }

    /**
     * Установить id board для трелло объекта.
     *
     * @param object   трелло объект
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void setIdBoard(TrelloObject object, JsonPath response){
        object.setIdBoard(response.getString(JSONPaths.ID_BOARD));
    }

    /**
     * Установить id колонки для трелло объекта.
     *
     * @param object   трелло объект
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void setIdList(TrelloObject object, JsonPath response){
        object.setIdList(response.getString(JSONPaths.ID_LIST));
    }

    /**
     * Установить id карточки для трелло объекта.
     *
     * @param object   трелло объект
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void setIdCard(TrelloObject object, JsonPath response){
        object.setIdCard(response.getString(JSONPaths.ID_CARD));
    }

    /**
     * Установить id контрольного списка.
     *
     * @param object   трелло объект
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void setIdCheckList(TrelloObject object, JsonPath response){
        object.setIdChecklist(response.getString(JSONPaths.ID_CHECKLIST));
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
        return PutRequests.CardRequests.updateCard(pathKey, pathValue, queryKey, queryValue);
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
        return PutRequests.ListRequests.updateList(pathKey, pathValue, queryKey, queryValue);
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
        return PostRequest.CardRequests.addStickerOnCard(pathKey, pathValue, params);
    }

    /**
     * Проверка, что id не null.
     *
     * @param response ответ в виде jsonpath
     */
    @Step
    public static void idNotNull(JsonPath response){
        Assert.assertNotNull(response.getString(JSONPaths.ID));
    }

}
