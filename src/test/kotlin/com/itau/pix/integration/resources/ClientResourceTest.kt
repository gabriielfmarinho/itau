package com.itau.pix.integration.resources

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.factory.CreateClientRequestFactory
import com.itau.pix.integration.config.IntegrationTest
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@DBRider
@IntegrationTest
class ClientResourceTest(
    private val createClientRequestFactory: CreateClientRequestFactory
) {

    @Test
    internal fun shouldSuccessfullyRegisterACustomer() {
        val clientToCreate = createClientRequestFactory.createDefault()
        RestAssured
            .given()
            .log().all()
            .body(clientToCreate)
            .`when`()
            .post("/clients")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .body("id", `is`(1))
    }

    @Test
    @DataSet(value = ["datasets/create-client-person-to-find.yaml"])
    internal fun shouldReturnStatusCode422WhenClientIsAlreadyRegistered() {
        val clientToCreate = createClientRequestFactory.createWith("GABRIEL", "FERREIRA")
        RestAssured
            .given()
            .log().all()
            .body(clientToCreate)
            .`when`()
            .post("/clients")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .body("status", `is`(422))
            .body("error", `is`("com.itau.pix.domain.exceptions.ClientAlreadyRegisteredException"))
            .body("message", `is`("client already registered in the base"))
    }
}
