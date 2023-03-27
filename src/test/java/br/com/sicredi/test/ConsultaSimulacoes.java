package br.com.sicredi.test;

import br.com.sicredi.base.RequestBase;
import br.com.sicredi.dto.SimulaDto;
import io.restassured.http.ContentType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.math.BigDecimal;
import static io.restassured.RestAssured.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsultaSimulacoes extends RequestBase {

    //Enviando e retornando o Status do código 200
    @Test
    public void testRetonaTodosInfoEStatus200() {


        given()
                .contentType(ContentType.JSON).

                when()
                .get("/v1/simulacoes").

                then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void testPreenchimentoTodasInfoEStatus201() {

        String nome = "Tarcio Almeida";
        String cpf = "21548643092";
        String email = "email01@email.com";
        BigDecimal valor = BigDecimal.valueOf(2000);
        int parcelas = 3;
        boolean seguro = true;
        SimulaDto simulacaoDTO = new SimulaDto(nome, cpf, email, valor, parcelas, seguro);

        given()
                .contentType(ContentType.JSON)
                .body(simulacaoDTO.converterParaJson()).

                when()
                .post("/v1/simulacoes").

                then()
                .assertThat()
                .statusCode(201);
    }
    @Test
    public void testComInfoIncompletasEStatus400() {

        String nome = "Tarcio Almeida 02";
        String cpf = "19080661058";
        String email = null;
        BigDecimal valor = BigDecimal.valueOf(2000);
        int parcelas = 3;
        boolean seguro = true;
        SimulaDto simulacaoDTO = new SimulaDto(nome, cpf, email, valor, parcelas, seguro);

        given()
                .contentType(ContentType.JSON)
                .body(simulacaoDTO.converterParaJson()).

                when()
                .post("/v1/simulacoes").

                then()
                .assertThat()
                .statusCode(400);
    }
    @Test
    public void testComCPFExistenteEStatus409() {

        String nome = "Tamires Oliveira";
        String cpf = "17822386034";
        String email = "teste@email.com";
        BigDecimal valor = BigDecimal.valueOf(30000);
        int parcelas = 12;
        boolean seguro = false;
        SimulaDto simulaDTO = new SimulaDto(nome, cpf, email, valor, parcelas, seguro);

        given()
                .contentType(ContentType.JSON)
                .body(simulaDTO.converterParaJson()).

                when()
                .post("/v1/simulacoes").

                then()
                .assertThat()
                .statusCode(409);
    }
    @Test
    public void testCpfSinulacoesExistentesStatus200() {

        String cpfParametro = "17822386034";

        given()
                .contentType(ContentType.JSON)
                .pathParam("cpf", cpfParametro).

                when()
                .get("/v1/simulacoes/{cpf}").

                then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void testCpfNaoAssociadoSinulacoesJaExistentesStatus404() {

        String cpfParametro = "12345678900";

        given()
                .contentType(ContentType.JSON)
                .pathParam("cpf", cpfParametro).

                when()
                .get("/v1/simulacoes/{cpf}").

                then()
                .assertThat()
                .statusCode(404);
    }
    @Test
    public void testCpfAssociadoASimulacaoExistenteStatus200() {

        String nome = "Tarcio Almeida 04";
        String cpf = null;
        String cpfParametro = "66414919004";
        String email = "email04@email.com";
        BigDecimal valor = BigDecimal.valueOf(2000);
        int parcelas = 40;
        boolean seguro = true;
        SimulaDto simulacaoDTO = new SimulaDto(nome, cpf, email, valor, parcelas, seguro);

        given()
                .contentType(ContentType.JSON)
                .pathParam("cpf", cpfParametro)
                .body(simulacaoDTO.converterParaJson()).

                when()
                .put("/v1/simulacoes/{cpf}").

                then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void testCpfNaoAssociadoASimulacaoExistenteStatus404() {

        String nome = "Tarcio Almeida 05";
        String cpf = null;
        String cpfp = "12345678900";
        String email = "email05@email.com";
        BigDecimal valor = BigDecimal.valueOf(10000);
        int parcelas = 36;
        boolean seguro = true;
        SimulaDto simulacaoDTO = new SimulaDto(nome, cpf, email, valor, parcelas, seguro);

        given()
                .contentType(ContentType.JSON)
                .pathParam("cpf", cpfp)
                .body(simulacaoDTO.converterParaJson()).

                when()
                .put("/v1/simulacoes/{cpf}").

                then()
                .assertThat()
                .statusCode(404);
    }
    @Test
    public void testDeletId() {

        String idp = "11";

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", idp).

                when()
                .delete("/v1/simulacoes/{id}").

                then()
                .assertThat()
                .statusCode(200);
    }
}