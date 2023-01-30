package api.requests;

import api.requests.specifications.RequestSpec;
import api.requests.specifications.ResponseSpec;
import endpoints.Endpoint;
import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest {

    public static class BoardRequests{
        public static JsonPath createBoard(Map<String, String> queryParam){
            return given()
                    .spec(RequestSpec.defaultRequestSpecification(queryParam))
                    .when()
                    .post(Endpoint.BOARD_DEFAULT)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }
    }

    public static class CardRequests{

        public static JsonPath createCard(HashMap<String, String> queryParams){
            return given()
                    .spec(RequestSpec.defaultRequestSpecification(queryParams))
                    .when()
                    .post(Endpoint.CARD_DEFAULT)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }

        public static JsonPath createAttachmentOnCard(String pathKey, String pathValue, File file){
            return given()
                    .spec(RequestSpec.requestWithAttachment(pathKey, pathValue))
                    .multiPart("file", file)
                    .when()
                    .post(Endpoint.ATTACHMENT_TO_CARD)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }

        public static JsonPath createCheckListOnCard(String pathKey, String pathValue, HashMap<String, String> queryParams){
            return given()
                    .spec(RequestSpec.requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams))
                    .when()
                    .post(Endpoint.CHECKLIST_ON_CARD)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }

        public static JsonPath addStickerOnCard(String pathKey, String pathValue, HashMap<String, String> queryParams){
            return given()
                    .spec(RequestSpec.requestWithPathParamAndQueryParams(pathKey, pathValue, queryParams))
                    .when()
                    .post(Endpoint.CARD_STICKER)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }
    }

    public static class ChecklistRequests {
        public static JsonPath createCheckItem(String pathKey, String pathValue, HashMap<String, String> queryParams){
            return given()
                    .spec(RequestSpec.requestWithPathParamAndQueryParams(pathKey, pathValue,queryParams))
                    .when()
                    .post(Endpoint.CHECK_ITEM)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }
    }

    public static class ListRequests {
        public static JsonPath createNewList(HashMap<String, String> queryParams) {
            return given()
                    .spec(RequestSpec.defaultRequestSpecification(queryParams))
                    .when()
                    .post(Endpoint.LISTS)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log()
                    .all()
                    .extract()
                    .jsonPath();
        }
    }
}
