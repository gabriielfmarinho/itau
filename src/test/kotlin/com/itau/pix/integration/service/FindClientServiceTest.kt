package com.itau.pix.integration.service

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.exceptions.ClientNotFoundException
import com.itau.pix.integration.config.IntegrationTest
import com.itau.pix.service.FindClientService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DBRider
@IntegrationTest
class FindClientServiceTest(
    val findClientService: FindClientService
) {

    @Test
    @DataSet(value = ["datasets/create-client-person-to-find.yaml"])
    internal fun shouldFindClientByFirstNameWithSuccess() {
        val firstNameToFind = "GABRIEL"
        val clientFound = findClientService.findByFirstName(firstNameToFind)
        assertNotNull(clientFound)
        assertEquals(firstNameToFind, clientFound.firstName)
    }

    @Test
    internal fun shouldThrowAClientNotFoundExceptionWhenFindClientByFirstName() {
        val firstNameToFind = "LEONARDO"
        assertThrows<ClientNotFoundException> {
            findClientService.findByFirstName(firstNameToFind)
        }
    }

    @Test
    internal fun shouldThrowAClientNotFoundExceptionWhenFindClientByFirstNameAndLastName() {
        val firstNameToFind = "LEONARDO"
        val lastNameToFind = "FERREIRA"
        assertThrows<ClientNotFoundException> {
            findClientService.findByFirstNameAndLastName(firstNameToFind, lastNameToFind)
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client-person-to-find.yaml"])
    internal fun shouldFindClientByFirstNameAndLastNameWithSuccess() {
        val firstNameToFind = "GABRIEL"
        val lastNameToFind = "FERREIRA"
        val clientFound = findClientService.findByFirstNameAndLastName(firstNameToFind, lastNameToFind)
        assertNotNull(clientFound)
        assertEquals(firstNameToFind, clientFound.firstName)
        assertEquals(lastNameToFind, clientFound.lastName)
    }
}
