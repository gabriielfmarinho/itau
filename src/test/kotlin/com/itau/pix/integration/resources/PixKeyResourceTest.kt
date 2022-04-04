package com.itau.pix.integration.resources

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.enums.KeyType
import com.itau.pix.factory.CreatePixKeyRequestFactory
import com.itau.pix.integration.config.IntegrationTest
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@DBRider
@IntegrationTest
class PixKeyResourceTest(
    private val createPixKeyRequestFactory: CreatePixKeyRequestFactory
) {

    @Test
    @DataSet(value = ["datasets/create-pix-key-to-find.yaml"])
    fun shouldGetPixKeyPagedWithSuccess() {
        RestAssured
            .given()
            .log().all()
            .`when`()
            .get("/pix-keys/paged")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .body("numberOfElements", `is`(1))
            .body("content[0].id", `is`("00dfd621-1681-4e72-9a24-f465968ed8a2"))
            .body("content.size()", `is`(1))
            .body("last", `is`(true))
    }

    @Test
    fun shouldReturnStatusCode422WhenStrategyNotExist() {
        val pixKeyToRequest = createPixKeyRequestFactory.createDefault()
        RestAssured
            .given()
            .log().all()
            .`when`()
            .body(pixKeyToRequest)
            .post("/pix-keys")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .body("status", `is`(422))
            .body("error", `is`("java.lang.IllegalArgumentException"))
            .body("message", `is`("The strategy to RANDOM does not exist"))
    }

    @Test
    fun shouldReturnStatusCode422WhenFieldsNotIsCorrectFormat() {
        val pixKeyToRequest = createPixKeyRequestFactory.createWith(KeyType.CPF, 12345, 123456789)
        RestAssured
            .given()
            .log().all()
            .`when`()
            .body(pixKeyToRequest)
            .post("/pix-keys")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .body("status", `is`(422))
            .body("error", `is`("org.springframework.web.bind.MethodArgumentNotValidException"))
            .body("messages.size()", `is`(2))
    }

    @Test
    fun shouldReturnStatusCode404WhenAccountNotFound() {
        val pixKeyToRequest = createPixKeyRequestFactory.createWith(KeyType.CPF, 1234, 123456)
        RestAssured
            .given()
            .log().all()
            .`when`()
            .body(pixKeyToRequest)
            .post("/pix-keys")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body("status", `is`(404))
            .body("error", `is`("com.itau.pix.domain.exceptions.AccountNotFoundException"))
            .body("message", `is`("your account not found"))
    }

    @Test
    fun shouldGetPixKeyPagedAndReturnStatusCode404BecausePixKeyNotExist() {
        RestAssured
            .given()
            .log().all()
            .`when`()
            .get("/pix-keys/paged")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body("status", `is`(404))
            .body("error", `is`("com.itau.pix.domain.exceptions.PixKeyNotFoundException"))
            .body("message", `is`("your pix key not found"))
    }

    @Test
    @DataSet(value = ["datasets/create-pix-key-to-find.yaml"])
    fun shouldGetPixKeyByIdWithSuccess() {
        RestAssured
            .given()
            .log().all()
            .`when`()
            .get("/pix-keys/{id}", "00dfd621-1681-4e72-9a24-f465968ed8a2")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .body("id", `is`("00dfd621-1681-4e72-9a24-f465968ed8a2"))
    }
}
