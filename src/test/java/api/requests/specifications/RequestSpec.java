package api.requests.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.UserUtils;

import java.util.Map;


/**
 * Спецификация запросов.
 */
public class RequestSpec {

    private static final String API_KEY = UserUtils.initUser().getApi_key();
    private static final String API_TOKEN = UserUtils.initUser().getApi_token();


    private static RequestSpecification baseRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com/")
                .addQueryParam("key", API_KEY)
                .addQueryParam("token", API_TOKEN)
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * Стандартный запрос с параметрами.
     *
     * @param params параметры запроса.
     * @return специфицация.
     */
    public static RequestSpecification defaultRequestSpecification(Map<String, String> params){
        return baseRequestSpecification()
                .queryParams(params);
    }

    /**
     * Запрос с параметром пути.
     *
     * @param key   ключ параметра пути
     * @param value значение параметра пути.
     * @return специцикация.
     */
    public static RequestSpecification requestWithPathParam(String key, String value){
        return baseRequestSpecification()
                .pathParam(key, value);
    }


    /**
     * Запрос с параметром пути и параметрами запроса
     *
     * @param pathParamKey   ключ параметра пути.
     * @param pathParamValue значение параметра пути.
     * @param queryParams    параметры запроса.
     * @return спецификация.
     */
    public static RequestSpecification requestWithPathParamAndQueryParams(String pathParamKey, String pathParamValue, Map<String, String> queryParams){
        return baseRequestSpecification()
                .pathParam(pathParamKey, pathParamValue)
                .queryParams(queryParams);
    }

    /**
     * Запрос с прикреплением файла.
     *
     * @param key   ключ параметра пути.
     * @param value значение параметра пути.
     * @return спецификация
     */
    public static RequestSpecification requestWithAttachment(String key, String value){
        return  baseRequestSpecification()
                .contentType(ContentType.MULTIPART)
                .pathParam(key, value);
    }

    /**
     * Запрос с параметрами пути и параметром запроса.
     *
     * @param pathParams параметры пути.
     * @param queryKey   ключ параметра запроса.
     * @param queryValue значение параметра запроса.
     * @return спецификация.
     */
    public static RequestSpecification requestWithPathParamsAndQueryParam(Map<String, String> pathParams, String queryKey, String queryValue ){
        return baseRequestSpecification()
                .pathParams(pathParams)
                .queryParam(queryKey, queryValue);
    }

    /**
     * Запрос с параметром пути и с параметром запроса.
     *
     * @param pathKey    ключ параметра пути.
     * @param pathValue  значение параметра пути.
     * @param queryKey   ключ параметра запроса.
     * @param queryValue значение параметра запроса.
     * @return спецификация.
     */
    public static RequestSpecification requestWithPathAndQueryParams(String pathKey, String pathValue, String queryKey, String queryValue){
        return baseRequestSpecification()
                .pathParam(pathKey, pathValue)
                .queryParam(queryKey, queryValue);
    }
}
