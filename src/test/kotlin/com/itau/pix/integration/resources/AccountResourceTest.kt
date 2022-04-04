package com.itau.pix.integration.resources

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.factory.CreateAccountRequestFactory
import com.itau.pix.integration.config.IntegrationTest
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@DBRider
@IntegrationTest
class AccountResourceTest(
    private val createAccountRequestFactory: CreateAccountRequestFactory
) {

    @Test
    @DataSet(value = ["datasets/create-client-person-to-find.yaml"])
    internal fun shouldSuccessfullyRegisterAAccount() {
        val accountToCreate = createAccountRequestFactory.createDefault()
        RestAssured
            .given()
            .log().all()
            .body(accountToCreate)
            .`when`()
            .post("/accounts")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .body("id", `is`(1))
    }

    @Test
    @DataSet(value = ["datasets/create-account-current-to-find.yaml"])
    internal fun shouldReturnStatusCode422WhenAccountIsAlreadyRegistered() {
        val accountToCreate = createAccountRequestFactory.createWith(1010, 202020)
        RestAssured
            .given()
            .log().all()
            .body(accountToCreate)
            .`when`()
            .post("/accounts")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .body("status", `is`(422))
            .body("error", `is`("com.itau.pix.domain.exceptions.AccountAlreadyExistException"))
            .body("message", `is`("account already registered in the base"))
    }
}
