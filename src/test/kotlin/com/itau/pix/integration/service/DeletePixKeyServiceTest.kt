package com.itau.pix.integration.service

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.integration.config.IntegrationTest
import com.itau.pix.service.DeletePixKeyService
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@DBRider
@IntegrationTest
class DeletePixKeyServiceTest(
    private val deletePixKeyService: DeletePixKeyService
) {

    @Test
    @DataSet(value = ["datasets/create-pix-key-email-to-find.yaml"])
    internal fun shouldInactivatedWithSuccessPixKeyById() {
        val pixKeyInactivated = deletePixKeyService.delete("00dfd621-1681-4e72-9a24-f465968ed8a2")
        assertNotNull(pixKeyInactivated)
    }
}