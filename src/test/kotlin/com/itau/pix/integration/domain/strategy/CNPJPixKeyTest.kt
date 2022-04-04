package com.itau.pix.integration.domain.strategy

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.enums.KeyType
import com.itau.pix.domain.exceptions.CNPJNotValidException
import com.itau.pix.domain.exceptions.EqualKeyException
import com.itau.pix.domain.exceptions.ExceededNumbersOfKeysException
import com.itau.pix.domain.strategy.CNPJPixKey
import com.itau.pix.factory.CreatePixKeyDtoFactory
import com.itau.pix.integration.config.IntegrationTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@DBRider
@IntegrationTest
class CNPJPixKeyTest(
    private val createPixKeyDtoFactory: CreatePixKeyDtoFactory,
    private val cnpjPixKey: CNPJPixKey
) {

    @Test
    internal fun shouldThrowACNPJNotValidExceptionWhenCpfIsInvalid() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CNPJ, "5611101131000148")
        assertThrows<CNPJNotValidException> { cnpjPixKey.create(pixKeyToCreate) }
    }

    @Test
    @DataSet(value = ["datasets/create-pix-key-cnpj-to-find.yaml"])
    internal fun shouldThrowAEqualKeyExceptionWhenValueKeyAlreadyExist() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CNPJ, "25901933000180")
        assertThrows<EqualKeyException> { cnpjPixKey.create(pixKeyToCreate) }
    }

    @Test
    @DataSet(value = ["datasets/create-many-pix-key-to-company.yaml"])
    internal fun shouldThrowAExceededNumbersOfKeysExceptionWhenExceededPixKeyPerUser() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CNPJ, "85061262000119")
        assertThrows<ExceededNumbersOfKeysException> { cnpjPixKey.create(pixKeyToCreate) }
    }

    @Test
    @DataSet(value = ["datasets/create-account-current.yaml"])
    internal fun shouldReturnAPixKeyRegisteredWithSuccess() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CNPJ, "25901933000180")
        val pixKey = assertDoesNotThrow { cnpjPixKey.create(pixKeyToCreate) }
        assertNotNull(pixKey)
    }
}