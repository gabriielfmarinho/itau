package com.itau.pix.integration.resources

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.enums.KeyType
import com.itau.pix.factory.CreatePixKeyRequestFactory
import com.itau.pix.factory.UpdatePixKeyRequestFactory
import com.itau.pix.integration.config.IntegrationTest
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.util.*

@DBRider
@IntegrationTest
class PixKeyResourceTest(
    private val createPixKeyRequestFactory: CreatePixKeyRequestFactory,
    private val updatePixKeyRequestFactory: UpdatePixKeyRequestFactory
) {

    @Test
    @DataSet(value = ["datasets/create-pix-key-email-to-find.yaml"])
    internal fun shouldGetPixKeyPagedWithSuccess() {
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
    @DataSet(value = ["datasets/create-pix-key-email-to-find.yaml"])
    internal fun shouldInactivePixKeyWithSuccess() {
        RestAssured
            .given()
            .log().all()
            .`when`()
            .delete("/pix-keys/{UUID}", "00dfd621-1681-4e72-9a24-f465968ed8a2")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .body("keyType", `is`("EMAIL"))
            .body("keyValue", `is`("gabriel.fmarinho@gmail.com"))
            .body("accountType", `is`("CURRENT"))
            .body("dateInactive", notNullValue())
            .body("dateCreate", notNullValue())
    }

    @Test
    @DataSet(value = ["datasets/create-pix-key-email-inactive.yaml"])
    internal fun shouldNotInactivePixKeyWhenAlreadyInactivate() {
        RestAssured
            .given()
            .log().all()
            .`when`()
            .delete("/pix-keys/{UUID}", "00dfd621-1681-4e72-9a24-f465968ed8a2")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .body("status", `is`(422))
            .body("error", `is`("com.itau.pix.domain.exceptions.PixKeyAlreadyInactivatedException"))
            .body("message", `is`("your pix key is already inactive"))
    }

    @Test
    internal fun shouldReturnStatusCode422WhenStrategyNotExist() {
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
            .body("message", `is`("the strategy to RANDOM does not exist"))
    }

    @Test
    @DataSet(value = ["datasets/create-pix-key-cpf-to-find.yaml", "datasets/create-account-savings.yaml"])
    internal fun shouldUpdatePixKeyWithSuccess() {
        val pixKeyToUpdate = updatePixKeyRequestFactory
            .createWith(2020, 101010, "GABRIEL", "FERREIRA")
        RestAssured
            .given()
            .log().all()
            .`when`()
            .body(pixKeyToUpdate)
            .patch("/pix-keys/{UUID}", "00dfd621-1681-4e72-9a24-f465968ed8a2")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test
    internal fun shouldReturnStatusCode422WhenFieldsNotIsCorrectFormat() {
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
    @DataSet(value = ["datasets/create-pix-key-email-to-find.yaml"])
    internal fun shouldReturnStatusCode422WhenPixKeyAlreadyExist() {
        val pixKeyToRequest = createPixKeyRequestFactory.createWith(KeyType.EMAIL, "gabriel.fmarinho@gmail.com")
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
            .body("error", `is`("com.itau.pix.domain.exceptions.EqualKeyException"))
            .body("message", `is`("pix key already registered in the base"))
    }

    @Test
    @DataSet(value = ["datasets/create-many-pix-key-to-person.yaml"])
    internal fun shouldReturnStatusCode422WhenExceedNumberOfKeysPerUser() {
        val pixKeyToRequest = createPixKeyRequestFactory.createWith(KeyType.EMAIL, "gabriel.fm@gmail.com")
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
            .body("error", `is`("com.itau.pix.domain.exceptions.ExceededNumbersOfKeysException"))
            .body("message", `is`("user exceeded number of keys"))
    }

    @Test
    internal fun shouldReturnStatusCode404WhenAccountNotFound() {
        val pixKeyToRequest = createPixKeyRequestFactory.createWith(KeyType.CPF, "08590665097")
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

    @ParameterizedTest
    @EnumSource(value = KeyType::class, names = ["CPF", "EMAIL", "CNPJ"])
    internal fun shouldReturnStatusCode422WhenKeyValueDoesNotHaveCorrectFormat(keyType: KeyType) {
        val pixKeyToRequest = createPixKeyRequestFactory.createWith(keyType, "anyValue")
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
            .body("error", notNullValue())
            .body("message", containsString("${keyType.name.lowercase()} is not valid"))
    }

    @Test
    internal fun shouldGetPixKeyPagedAndReturnStatusCode404BecausePixKeyNotExist() {
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
    @DataSet(value = ["datasets/create-pix-key-email-to-find.yaml"])
    internal fun shouldGetPixKeyByIdWithSuccess() {
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
