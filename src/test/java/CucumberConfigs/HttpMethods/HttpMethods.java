package CucumberConfigs.HttpMethods;

import CucumberConfigs.RequestResponseSpecification.RequestResponseSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpMethods {
    public Response post(String url,Object payload){
               return   given()
                            .spec(RequestResponseSpecification.REQUEST_SPECIFICATION).body(payload)
                        .when()
                            .post(url)
                        .then()
                            .spec(RequestResponseSpecification.RESPONSE_SPECIFICATION).extract().response();
    }

    public Response get(String url){
                return   given()
                            .spec(RequestResponseSpecification.REQUEST_SPECIFICATION)
                         .when()
                            .get(url)
                         .then()
                            .spec(RequestResponseSpecification.RESPONSE_SPECIFICATION).extract().response();
    }

    public Response put(String url,Object payload){
                return   given()
                            .spec(RequestResponseSpecification.REQUEST_SPECIFICATION).body(payload)
                         .when()
                            .put(url)
                         .then()
                            .spec(RequestResponseSpecification.RESPONSE_SPECIFICATION).extract().response();
    }

}
