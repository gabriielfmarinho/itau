package com.itau.pix.integration.resources

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.integration.config.IntegrationTest
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@DBRider
@IntegrationTest
class PixKeyResourceTest {

    @Test
    @DataSet(value = arrayOf("datasets/create-pix-key-to-find.yaml"))
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
    @DataSet(value = arrayOf("datasets/create-pix-key-to-find.yaml"))
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
