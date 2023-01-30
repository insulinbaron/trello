package api.requests;

import api.requests.specifications.RequestSpec;
import api.requests.specifications.ResponseSpec;
import endpoints.Endpoint;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class GetRequests {

    public static class CardRequests{
        public static JsonPath getCard(String key, String value){
            return given()
                    .spec(RequestSpec.requestWithPathParam(key, value))
                    .when()
                    .get(Endpoint.CARD_PATH)
                    .then()
                    .spec(ResponseSpec.defaultResponseSpec())
                    .log().all()
                    .extract().jsonPath();
        }
    }
}
