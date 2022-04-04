package com.itau.pix.integration.domain.strategy

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.enums.KeyType
import com.itau.pix.domain.exceptions.CPFNotValidException
import com.itau.pix.domain.exceptions.EqualKeyException
import com.itau.pix.domain.exceptions.ExceededNumbersOfKeysException
import com.itau.pix.domain.strategy.CPFPixKey
import com.itau.pix.factory.CreatePixKeyDtoFactory
import com.itau.pix.integration.config.IntegrationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@DBRider
@IntegrationTest
class CPFPixKeyTest(
    private val createPixKeyDtoFactory: CreatePixKeyDtoFactory,
    private val cpfPixKey: CPFPixKey
) {

    @Test
    internal fun shouldThrowACPFNotValidExceptionWhenCpfIsInvalid() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CPF, "86140818030")
        assertThrows<CPFNotValidException> { cpfPixKey.create(pixKeyToCreate) }
    }

    @Test
    @DataSet(value = ["datasets/create-pix-key-cpf-to-find.yaml"])
    internal fun shouldThrowAEqualKeyExceptionWhenValueKeyAlreadyExist() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.EMAIL, "54696683044")
        assertThrows<EqualKeyException> { cpfPixKey.create(pixKeyToCreate) }
    }

    @Test
    @DataSet(value = ["datasets/create-many-pix-key-to-company.yaml"])
    internal fun shouldThrowAExceededNumbersOfKeysExceptionWhenExceededPixKeyPerUser() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CPF, "89164055051")
        assertThrows<ExceededNumbersOfKeysException> { cpfPixKey.create(pixKeyToCreate) }
    }

    @Test
    @DataSet(value = ["datasets/create-account-current.yaml"])
    internal fun shouldReturnAPixKeyRegisteredWithSuccess() {
        val pixKeyToCreate = createPixKeyDtoFactory.createWith(KeyType.CPF, "54696683044")
        val pixKey = assertDoesNotThrow { cpfPixKey.create(pixKeyToCreate) }
        Assertions.assertNotNull(pixKey)
    }
}