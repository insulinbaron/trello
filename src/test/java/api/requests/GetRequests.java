package api.requests;

import api.requests.specifications.RequestSpec;
import api.requests.specifications.ResponseSpec;
import io.restassured.RestAssured;

import endpoints.HTTPEndpoints;
import io.restassured.path.json.JsonPath;
import logger.Log;


/**
 * The type Get requests.
 */
public class GetRequests {
    /**
     * Запрос с параметром пути.
     * @param key ключ параметра пути.
     * @param value значение параметра пути.
     * @param httpEndpoint конечная точка http на которую будет отправлен запрос.
     * @return
     */
    private static JsonPath requestWithPathParam(String key, String value, String httpEndpoint){
        Log.info("Отправляем GET запрос \nendpoint: " + httpEndpoint);
        return RestAssured.given()
                .spec(RequestSpec.requestWithPathParam(key, value))
                .when()
                .get(httpEndpoint)
                .then()
                .spec(ResponseSpec.defaultResponseSpec())
                .log().all()
                .extract().jsonPath();
    }

    /**
     * The type Card requests.
     */
    public static class CardRequests{
        /**
         * Получить карточку.
         *
         * @param key   ключ параметра пути.
         * @param value значение параметра пути.
         * @return the json path
         */
        public static JsonPath getCard(String key, String value){
            return requestWithPathParam(key, value, HTTPEndpoints.CARD_PATH);
        }
    }
}
