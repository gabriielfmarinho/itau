package com.itau.pix.integration.service

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.exceptions.AccountNotFoundException
import com.itau.pix.domain.exceptions.CustomerDifferentFromAccountException
import com.itau.pix.factory.UpdatePixKeyDtoFactory
import com.itau.pix.integration.config.IntegrationTest
import com.itau.pix.service.UpdatePixKeyService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DBRider
@IntegrationTest
class UpdatePixKeyServiceTest(
    private val updatePixKeyService: UpdatePixKeyService,
    private val updatePixKeyDtoFactory: UpdatePixKeyDtoFactory
) {

    @Test
    @DataSet(value = ["datasets/create-pix-key-cpf-to-find.yaml", "datasets/create-account-savings.yaml"])
    internal fun shouldUpdateAccountInPixKeyWithSuccess() {
        val pixKeyToUpdate = updatePixKeyDtoFactory
            .createWith(2020, 101010, "GABRIEL", "FERREIRA")
        val pixKeyUpdated = updatePixKeyService.update("00dfd621-1681-4e72-9a24-f465968ed8a2", pixKeyToUpdate)
        assertNotNull(pixKeyUpdated)
        assertEquals(2, pixKeyUpdated.account.id)
    }

    @Test
    @DataSet(value = ["datasets/create-pix-key-cpf-to-find.yaml"])
    internal fun shouldThrownAAccountNotFoundExceptionWhenNotFoundAccount() {
        val pixKeyToUpdate = updatePixKeyDtoFactory
            .createWith(2020, 101010, "GABRIEL", "FERREIRA")
        assertThrows<AccountNotFoundException> {
            updatePixKeyService.update("00dfd621-1681-4e72-9a24-f465968ed8a2", pixKeyToUpdate)
        }
    }

    @Test
    @DataSet(
        value = ["datasets/create-pix-key-cpf-to-find.yaml",
            "datasets/create-account-savings-another-client.yaml"]
    )
    internal fun shouldThrownACustomerDifferentFromAccountExceptionWhenNotFoundAccount() {
        val pixKeyToUpdate = updatePixKeyDtoFactory
            .createWith(2020, 101010, "GABRIEL", "FERREIRA")
        assertThrows<CustomerDifferentFromAccountException> {
            updatePixKeyService.update("00dfd621-1681-4e72-9a24-f465968ed8a2", pixKeyToUpdate)
        }
    }
}