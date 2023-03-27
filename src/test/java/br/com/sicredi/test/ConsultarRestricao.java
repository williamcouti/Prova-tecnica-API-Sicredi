package br.com.sicredi.test;

import br.com.sicredi.base.RequestBase;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class ConsultarRestricao extends RequestBase {

    //Cenário 1
    @Test
    public void testCpfComRestricaoStatus200(){

        //CPF que possui restrições
        given()
            .contentType(ContentType.JSON)
            .pathParam("cpf", "58063164083").
        when()
            .get("/v1/restricoes/{cpf}").
        then()
            .assertThat()
            .statusCode(200).
            body("mensagem", is("CPF 84809766080 possui restrição"));
    }

    @Test
    public void testeCpfSemRestricaoStatus204(){

        //CPF que não possui restrições
        given()
            .contentType(ContentType.JSON)
            .pathParam("cpf", "08574590665").
        when()
            .get("/v1/restricoes/{cpf}").
        then()
            .assertThat()
            .statusCode(204);
    }
}
