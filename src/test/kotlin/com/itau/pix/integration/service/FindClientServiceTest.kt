package com.itau.pix.integration.service

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.exceptions.ClientNotFoundException
import com.itau.pix.integration.config.IntegrationTest
import com.itau.pix.service.FindClientService
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DBRider
@IntegrationTest
class FindClientServiceTest(
    val findClientService: FindClientService
) {

    @Test
    @DataSet(value = arrayOf("datasets/create-client-to-find.yaml"))
    fun shouldFindClientByFirstNameWithSuccess() {
        val firstNameToFind = "GABRIEL"
        val clientFound = findClientService.findByFirstName(firstNameToFind)
        assertNotNull(clientFound)
    }

    @Test
    fun shouldThrowAClientNotFoundExceptionWhenFindClientByFirstName() {
        val firstNameToFind = "LEONARDO"
        assertThrows<ClientNotFoundException> {
            findClientService.findByFirstName(firstNameToFind)
        }
    }
}
