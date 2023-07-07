package CucumberConfigs.RequestResponseSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.*;

public class RequestResponseSpecification {

    public static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
                                                                        .log(LogDetail.ALL)
                                                                        .addHeader("Content-Type","application/json")
                                                                        .build();

    public static final ResponseSpecification RESPONSE_SPECIFICATION = new ResponseSpecBuilder()
                                                                        .log(LogDetail.ALL)
                                                                        .build();
}