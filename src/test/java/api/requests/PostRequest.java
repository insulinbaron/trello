package api.requests;

import api.requests.specifications.RequestSpec;
import api.requests.specifications.ResponseSpec;
import endpoints.HTTPEndpoints;
import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * The type Post request.
 */
public class PostRequest {

    /**
     * Запрос с параметрами
     *
     * @param queryParam параметры запроса
     * @param httpEndpoint конечная точка http запроса.
     * @return
     */
    private static JsonPath requestWithQueryParams(HashMap<String, String> queryParam, String httpEndpoint){
        return given()
                .spec(RequestSpec.defaultRequestSpecification(queryParam))
                .when()
                .post(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * Request with path parameter and query parameters.
     *
     * @param pathKey ключ параметра пути.
     * @param pathValue значение параметра пути.
     * @param queryParams параметры запроса.
     * @param httpEndpoint конечная точка http запроса.
     * @return
     */

    private static JsonPath requestWithPathParamAndQueryParams(String pathKey, String pathValue, HashMap<String, String> queryParams, String httpEndpoint){
        return given()
                .spec(RequestSpec.requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams))
                .when()
                .post(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * Запрос с прикреплением файла
     *
     * @param pathKey ключ параметра пути
     * @param pathValue значение параметра пути
     * @param file прикрепляемый файл
     * @param httpEndpoint конечная точка http запроса
     * @return JsonPath
     */
    private static JsonPath requestWithAttachment(String pathKey, String pathValue, File file, String httpEndpoint){
        return given()
                .spec(RequestSpec.requestWithAttachment(pathKey, pathValue))
                .multiPart("file", file)
                .when()
                .post(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * Запросы связанные с доской трелло.
     */
    public static class BoardRequests{
        /**
         * Создание доски.
         *
         * @param queryParam параметры пути
         * @return the json path
         */
        public static JsonPath createBoard(HashMap<String, String> queryParam){
            return requestWithQueryParams(queryParam, HTTPEndpoints.BOARD_DEFAULT);
        }
    }

    /**
     * Запросы связанные с карточкой трелло.
     */
    public static class CardRequests{

        /**
         * Создание карточки.
         *
         * @param queryParams параметры запроса
         * @return the json path
         */
        public static JsonPath createCard(HashMap<String, String> queryParams){
            return requestWithQueryParams(queryParams, HTTPEndpoints.CARD_DEFAULT);
        }

        /**
         * Прикрепление вложения на карточку.
         *
         * @param pathKey   ключ параметра пути
         * @param pathValue значение параметра пути
         * @param file      файл для прикрепления на карточку
         * @return the json path
         */
        public static JsonPath createAttachmentOnCard(String pathKey, String pathValue, File file){
            return requestWithAttachment(pathKey, pathValue, file, HTTPEndpoints.ATTACHMENT_TO_CARD);
        }

        /**
         * Создание контрольного списка на карточке.
         *
         * @param pathKey     ключ параметра пути
         * @param pathValue   значение параметра пути
         * @param queryParams параметры запроса
         * @return the json path
         */
        public static JsonPath createCheckListOnCard(String pathKey, String pathValue, HashMap<String, String> queryParams) {
            return requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams, HTTPEndpoints.CHECKLIST_ON_CARD);
        }

        /**
         * Добавление стикера на карточку.
         *
         * @param pathKey     ключ параметра пути
         * @param pathValue   значение параметра пути
         * @param queryParams параметры запроса
         * @return the json path
         */
        public static JsonPath addStickerOnCard(String pathKey, String pathValue, HashMap<String, String> queryParams){
            return requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams, HTTPEndpoints.CARD_STICKER);
        }
    }

    /**
     * Запросы связанные с контрольными пунктами
     */
    public static class CheckItemRequests {
        /**
         * Создание контрольного списка.
         *
         * @param pathKey     ключ параметра пути
         * @param pathValue   значение параметра пути
         * @param queryParams параметры запроса
         * @return the json path
         */
        public static JsonPath createCheckItem(String pathKey, String pathValue, HashMap<String, String> queryParams){
            return requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams, HTTPEndpoints.CHECK_ITEM);
        }
    }

    /**
     * Запросы связанные с колонкой трелло.
     */
    public static class ListRequests {
        /**
         * Создание новой колонки.
         *
         * @param queryParams параметры запроса
         * @return the json path
         */
        public static JsonPath createNewList(HashMap<String, String> queryParams) {
            return requestWithQueryParams(queryParams, HTTPEndpoints.LISTS);
        }
    }
}
