package api.requests.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

/**
 * Спецификация ответов
 */
public class ResponseSpec {
    /**
     * Стандартная спецификация ответа.
     *
     * @return спецификация
     */
    public static ResponseSpecification defaultResponseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}

