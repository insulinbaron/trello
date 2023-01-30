//package api.requests.specifications;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//import utils.UserUtils;
//
//import java.util.Map;
//
//
//public class RequestSpec {
//
//    private static final String API_KEY = UserUtils.initUser().getApi_key();
//    private static final String API_TOKEN = UserUtils.initUser().getApi_token();
//
//
//    public static RequestSpecification baseRequestSpecification(){
//        return new RequestSpecBuilder()
//                .setBaseUri("https://api.trello.com/")
//                .addQueryParam("key", API_KEY)
//                .addQueryParam("token", API_TOKEN)
//                .build();
//    }
//
//    public static RequestSpecification defaultRequestSpecification(Map<String, String> param){
//        return baseRequestSpecification()
//                .contentType(ContentType.JSON)
//                .queryParams(param);
//    }
//
//    public static RequestSpecification requestWithPathParam(String key, String value){
//        return baseRequestSpecification()
//                .contentType(ContentType.JSON)
//                .pathParams(key, value);
//    }
//
//
//    public static RequestSpecification requestWithPathParamAndQueryParams(String pathParamKey, String pathParamValue, Map<String, String> queryParams){
//        return baseRequestSpecification()
//                .contentType(ContentType.JSON)
//                .pathParam(pathParamKey, pathParamValue)
//                .queryParams(queryParams);
//    }
//
//    public static RequestSpecification requestWithAttachment(String key, String value){
//        return  baseRequestSpecification()
//                .contentType(ContentType.MULTIPART)
//                .pathParam(key, value);
//    }
//
//    public static RequestSpecification requestWithPathParamsAndQueryParam(Map<String, String> pathParams, String queryKey, String queryValue ){
//        return baseRequestSpecification()
//                .contentType(ContentType.JSON)
//                .pathParams(pathParams)
//                .queryParam(queryKey, queryValue);
//    }
//
//    public static RequestSpecification requestWithPathAndQueryParams(String pathKey, String pathValue, String queryKey, String queryValue){
//        return baseRequestSpecification()
//                .contentType(ContentType.JSON)
//                .pathParam(pathKey, pathValue)
//                .queryParam(queryKey, queryValue);
//    }
//}
