package api.requests;

import api.requests.specifications.RequestSpec;
import api.requests.specifications.ResponseSpec;
import endpoints.HTTPEndpoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import logger.Log;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * The type Put requests.
 */
public class PutRequests {
    /**
     * Запрос с параметром пути и параметрами запроса
     *
     * @param pathKey ключ параметра пути
     * @param pathValue значение параметра пути
     * @param queryParams параметры запроса
     * @param httpEndpoint конечная точка http запроса
     * @return jsonpath
     */

    private static JsonPath requestWithPathParamAndQueryParams(String pathKey, String pathValue, Map<String, String> queryParams, String httpEndpoint){
        Log.info("Отправляем PUT запрос \nendpoint: " + httpEndpoint);
        return RestAssured.given()
                .spec(RequestSpec.requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams))
                .when()
                .put(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * Запрос с параметром пути и параметром запроса
     *
     * @param pathKey ключ параметра пути
     * @param pathValue значение параметра пути
     * @param queryKey ключ параметра запроса
     * @param queryValue значение параметра запроса
     * @param httpEndpoint конечная точка http запроса
     * @return jsonpath
     */

    private static JsonPath requestWithPathAndQueryParams(String pathKey, String pathValue, String queryKey, String queryValue, String httpEndpoint){
        Log.info("Отправляем PUT запрос \nendpoint: " + httpEndpoint);
        return RestAssured.given()
                .spec(RequestSpec.requestWithPathAndQueryParams(pathKey,pathValue,queryKey,queryValue))
                .when()
                .put(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * Запрос с параметрами пути и параметром запроса
     *
     * @param pathParams параметры пути
     * @param queryKey ключ параметра запроса
     * @param queryValue значение параметра запроса
     * @param httpEndpoint конечная точка http запроса
     * @return
     */

    private static JsonPath requestWithPathParamsAndQueryParam(HashMap<String, String> pathParams, String queryKey, String queryValue, String httpEndpoint){
        Log.info("Отправляем PUT запрос \nendpoint: " + httpEndpoint);
        return RestAssured.given()
                .spec(RequestSpec.requestWithPathParamsAndQueryParam(pathParams, queryKey, queryValue))
                .when()
                .put(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * Запросы связанные с карточкой трелло.
     */
    public static class CardRequests{
        /**
         * Обновить карточку .
         *
         * @param pathKey     ключ параметра пути
         * @param pathValue   значение параметра пути
         * @param queryParams параметры запроса
         * @return the json path
         */
        public static JsonPath updateCard(String pathKey, String pathValue, Map<String, String> queryParams){
            return requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams, HTTPEndpoints.CARD_PATH);
        }

        /**
         * Обновить карточку.
         *
         * @param pathKey    ключ параметра пути
         * @param pathValue  значение параметра пути
         * @param queryKey   ключ параметра запроса
         * @param queryValue значение параметра запроса
         * @return the json path
         */
        public static JsonPath updateCard(String pathKey, String pathValue, String queryKey, String queryValue){
            return requestWithPathAndQueryParams(pathKey, pathValue, queryKey, queryValue, HTTPEndpoints.CARD_PATH);
        }

        /**
         * Обновление контрольного списка.
         *
         * @param pathParams параметры пути
         * @param queryKey   ключ параметра запроса
         * @param queryValue значение параметра запроса
         * @return the json path
         */
        public static JsonPath updateCheckItem(HashMap<String, String> pathParams, String queryKey, String queryValue){
            return requestWithPathParamsAndQueryParam(pathParams, queryKey, queryValue, HTTPEndpoints.CHECKITEM_ON_CARD);
        }
    }

    /**
     * The type List requests.
     */
    public static class ListRequests{
        /**
         * Обновление колонки.
         *
         * @param pathKey    ключ параметра пути
         * @param pathValue  значение параметра пути
         * @param queryKey   ключ параметра запроса
         * @param queryValue значение параметра запроса
         * @return the json path
         */
        public static JsonPath updateList(String pathKey, String pathValue, String queryKey, String queryValue){
            return requestWithPathAndQueryParams(pathKey, pathValue, queryKey, queryValue, HTTPEndpoints.LIST_PATH);
        }
    }
}
