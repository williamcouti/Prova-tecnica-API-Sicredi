package br.com.sicredi.base;

import io.restassured.config.RestAssuredConfig;
import org.junit.Before;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
public class RequestBase {

    @Before
    public void preCondicao() {

        baseURI = "http://localhost";
        basePath = "/api";
        port = 8080;

        config = RestAssuredConfig.newConfig().
                jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));

        enableLoggingOfRequestAndResponseIfValidationFails();

    }

}
