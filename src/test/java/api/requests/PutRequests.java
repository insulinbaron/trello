//package api.requests;
//
//import api.requests.specifications.RequestSpec;
//import api.requests.specifications.ResponseSpec;
//import endpoints.Endpoint;
//import io.restassured.path.json.JsonPath;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//
//public class PutRequests {
//    public static class CardRequests{
//        public static JsonPath updateCard(String pathKey, String pathValue, Map<String, String> queryParams){
//            return given()
//                    .spec(RequestSpec.requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams))
//                    .when()
//                    .put(Endpoint.CARD_PATH)
//                    .then()
//                    .spec(ResponseSpec.defaultResponseSpec())
//                    .log().all()
//                    .extract().jsonPath();
//        }
//
//        public static JsonPath updateCard(String pathKey, String pathValue, String queryKey, String queryValue){
//            return given()
//                    .spec(RequestSpec.requestWithPathAndQueryParams(pathKey,pathValue,queryKey,queryValue))
//                    .when()
//                    .put(Endpoint.CARD_PATH)
//                    .then()
//                    .spec(ResponseSpec.defaultResponseSpec())
//                    .log().all()
//                    .extract().jsonPath();
//        }
//
//        public static JsonPath updateCheckItem(HashMap<String, String> pathParams, String queryKey, String queryValue){
//            return given()
//                    .spec(RequestSpec.requestWithPathParamsAndQueryParam(pathParams, queryKey, queryValue))
//                    .when()
//                    .put(Endpoint.CHECKITEM_ON_CARD)
//                    .then()
//                    .spec(ResponseSpec.defaultResponseSpec())
//                    .log().all()
//                    .extract().jsonPath();
//        }
//    }
//
//    public static class ListRequests{
//        public static JsonPath updateList(String pathKey, String pathValue, String queryKey, String queryValue){
//            return given()
//                    .spec(RequestSpec.requestWithPathAndQueryParams(pathKey, pathValue, queryKey, queryValue))
//                    .when()
//                    .put(Endpoint.LIST_PATH)
//                    .then()
//                    .spec(ResponseSpec.defaultResponseSpec())
//                    .log().all()
//                    .extract().jsonPath();
//        }
//    }
//}
