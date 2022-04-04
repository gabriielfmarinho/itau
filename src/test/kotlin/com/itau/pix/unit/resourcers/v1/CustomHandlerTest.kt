package com.itau.pix.unit.resourcers.v1

import com.itau.pix.domain.exceptions.AccountAlreadyExistException
import com.itau.pix.domain.exceptions.AccountNotFoundException
import com.itau.pix.domain.exceptions.CNPJNotValidException
import com.itau.pix.domain.exceptions.CPFNotValidException
import com.itau.pix.domain.exceptions.ClientAlreadyRegisteredException
import com.itau.pix.domain.exceptions.ClientNotFoundException
import com.itau.pix.domain.exceptions.CustomerDifferentFromAccountException
import com.itau.pix.domain.exceptions.EmailNotValidException
import com.itau.pix.domain.exceptions.EqualKeyException
import com.itau.pix.domain.exceptions.ExceededNumbersOfKeysException
import com.itau.pix.domain.exceptions.PixKeyAlreadyInactivatedException
import com.itau.pix.domain.exceptions.PixKeyNotFoundException
import com.itau.pix.resources.v1.CustomHandler
import com.itau.pix.unit.config.UnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import javax.validation.UnexpectedTypeException

@UnitTest
class CustomHandlerTest {

    private lateinit var customerHandler: CustomHandler

    @BeforeEach
    internal fun setUp() {
        customerHandler = CustomHandler()
    }

    @Test
    internal fun shouldHandleClientNotFoundExceptionWithSuccess() {
        val handleClientNotFoundException = customerHandler
            .handleClientNotFoundException(ClientNotFoundException())
        assertNotNull(handleClientNotFoundException)
        assertEquals(HttpStatus.NOT_FOUND, handleClientNotFoundException.statusCode)
        assertEquals(CustomHandler.CLIENT_MESSAGE_NOT_FOUND, handleClientNotFoundException.body?.message)
        assertEquals(ClientNotFoundException::class.qualifiedName, handleClientNotFoundException.body?.error)
        assertEquals(HttpStatus.NOT_FOUND.value(), handleClientNotFoundException.body?.status)
    }

    @Test
    internal fun shouldHandleAccountNotFoundExceptionWithSuccess() {
        val handleAccountNotFoundException = customerHandler
            .handleAccountNotFoundException(AccountNotFoundException())
        assertNotNull(handleAccountNotFoundException)
        assertEquals(HttpStatus.NOT_FOUND, handleAccountNotFoundException.statusCode)
        assertEquals(CustomHandler.ACCOUNT_MESSAGE_NOT_FOUND, handleAccountNotFoundException.body?.message)
        assertEquals(AccountNotFoundException::class.qualifiedName, handleAccountNotFoundException.body?.error)
        assertEquals(HttpStatus.NOT_FOUND.value(), handleAccountNotFoundException.body?.status)
    }

    @Test
    internal fun shouldHandlePixKeyNotFoundExceptionWithSuccess() {
        val handlePixKeyNotFoundException = customerHandler
            .handlePixKeyNotFoundException(PixKeyNotFoundException())
        assertNotNull(handlePixKeyNotFoundException)
        assertEquals(HttpStatus.NOT_FOUND, handlePixKeyNotFoundException.statusCode)
        assertEquals(CustomHandler.PIX_KEY_MESSAGE_NOT_FOUND, handlePixKeyNotFoundException.body?.message)
        assertEquals(PixKeyNotFoundException::class.qualifiedName, handlePixKeyNotFoundException.body?.error)
        assertEquals(HttpStatus.NOT_FOUND.value(), handlePixKeyNotFoundException.body?.status)
    }

    @Test
    internal fun shouldHandleCPFNotValidExceptionWithSuccess() {
        val handleCPFNotValidException = customerHandler
            .handleCPFNotValidException(CPFNotValidException())
        assertNotNull(handleCPFNotValidException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleCPFNotValidException.statusCode)
        assertEquals(CustomHandler.MESSAGE_CPF_NOT_VALID, handleCPFNotValidException.body?.message)
        assertEquals(CPFNotValidException::class.qualifiedName, handleCPFNotValidException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleCPFNotValidException.body?.status)
    }

    @Test
    internal fun shouldHandleCNPJNotValidExceptionWithSuccess() {
        val handleCNPJNotValidException = customerHandler
            .handleCNPJNotValidException(CNPJNotValidException())
        assertNotNull(handleCNPJNotValidException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleCNPJNotValidException.statusCode)
        assertEquals(CustomHandler.MESSAGE_CNPJ_NOT_VALID, handleCNPJNotValidException.body?.message)
        assertEquals(CNPJNotValidException::class.qualifiedName, handleCNPJNotValidException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleCNPJNotValidException.body?.status)
    }

    @Test
    internal fun shouldHandleEmailNotValidExceptionWithSuccess() {
        val handleEmailNotValidException = customerHandler
            .handleEmailNotValidException(EmailNotValidException())
        assertNotNull(handleEmailNotValidException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleEmailNotValidException.statusCode)
        assertEquals(CustomHandler.MESSAGE_EMAIL_NOT_VALID, handleEmailNotValidException.body?.message)
        assertEquals(EmailNotValidException::class.qualifiedName, handleEmailNotValidException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleEmailNotValidException.body?.status)
    }

    @Test
    internal fun shouldHandleClientAlreadyRegisteredExceptionWithSuccess() {
        val handleClientAlreadyRegisteredException = customerHandler
            .handleClientAlreadyRegisteredException(ClientAlreadyRegisteredException())
        assertNotNull(handleClientAlreadyRegisteredException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleClientAlreadyRegisteredException.statusCode)
        assertEquals(CustomHandler.MESSAGE_CLIENT_ALREADY_REGISTERED, handleClientAlreadyRegisteredException.body?.message)
        assertEquals(ClientAlreadyRegisteredException::class.qualifiedName, handleClientAlreadyRegisteredException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleClientAlreadyRegisteredException.body?.status)
    }

    @Test
    internal fun shouldHandleEqualKeyExceptionWithSuccess() {
        val handleEqualKeyException = customerHandler
            .handleEqualKeyException(EqualKeyException())
        assertNotNull(handleEqualKeyException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleEqualKeyException.statusCode)
        assertEquals(CustomHandler.MESSAGE_PIX_KEY_ALREADY_REGISTERED, handleEqualKeyException.body?.message)
        assertEquals(EqualKeyException::class.qualifiedName, handleEqualKeyException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleEqualKeyException.body?.status)
    }

    @Test
    internal fun shouldHandleExceededNumbersOfKeysExceptionWithSuccess() {
        val handleExceededNumbersOfKeysException = customerHandler
            .handleExceededNumbersOfKeysException(ExceededNumbersOfKeysException())
        assertNotNull(handleExceededNumbersOfKeysException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleExceededNumbersOfKeysException.statusCode)
        assertEquals(CustomHandler.MESSAGE_EXCEEDED_NUMBER_OF_KEYS, handleExceededNumbersOfKeysException.body?.message)
        assertEquals(ExceededNumbersOfKeysException::class.qualifiedName, handleExceededNumbersOfKeysException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleExceededNumbersOfKeysException.body?.status)
    }

    @Test
    internal fun shouldHandleAccountAlreadyExistExceptionWithSuccess() {
        val handleAccountAlreadyExistException = customerHandler
            .handleAccountAlreadyExistException(AccountAlreadyExistException())
        assertNotNull(handleAccountAlreadyExistException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleAccountAlreadyExistException.statusCode)
        assertEquals(CustomHandler.MESSAGE_ACCOUNT_ALREADY_REGISTERED, handleAccountAlreadyExistException.body?.message)
        assertEquals(AccountAlreadyExistException::class.qualifiedName, handleAccountAlreadyExistException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleAccountAlreadyExistException.body?.status)
    }

    @Test
    internal fun shouldHandleCustomerDifferentFromAccountExceptionWithSuccess() {
        val handleCustomerDifferentFromAccountException = customerHandler
            .handleCustomerDifferentFromAccountException(CustomerDifferentFromAccountException())
        assertNotNull(handleCustomerDifferentFromAccountException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleCustomerDifferentFromAccountException.statusCode)
        assertEquals(CustomHandler.MESSAGE_CUSTOMER_DOES_NOT_HAVE_AN_ACCOUNT, handleCustomerDifferentFromAccountException.body?.message)
        assertEquals(CustomerDifferentFromAccountException::class.qualifiedName, handleCustomerDifferentFromAccountException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleCustomerDifferentFromAccountException.body?.status)
    }

    @Test
    internal fun shouldHandleExceptionWithSuccessWhenExceptionAHaveMessage() {
        val anyMessage = "any message"
        val handleException = customerHandler
            .handleException(Exception(anyMessage))
        assertNotNull(handleException)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handleException.statusCode)
        assertEquals(anyMessage, handleException.body?.message)
        assertEquals(Exception::class.qualifiedName, handleException.body?.error)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), handleException.body?.status)
    }

    @Test
    internal fun shouldHandleExceptionWithSuccessWhenExceptionNotHaveMessage() {
        val handleException = customerHandler
            .handleException(Exception())
        assertNotNull(handleException)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handleException.statusCode)
        assertNull(handleException.body)
    }

    @Test
    internal fun shouldHandleUnexpectedTypeExceptionWithSuccessWhenExceptionAHaveMessage() {
        val anyMessage = "any message"
        val handleUnexpectedTypeException = customerHandler
            .handleUnexpectedTypeException(UnexpectedTypeException(anyMessage))
        assertNotNull(handleUnexpectedTypeException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleUnexpectedTypeException.statusCode)
        assertEquals(anyMessage, handleUnexpectedTypeException.body?.message)
        assertEquals(UnexpectedTypeException::class.qualifiedName, handleUnexpectedTypeException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleUnexpectedTypeException.body?.status)
    }

    @Test
    internal fun shouldHandleUnexpectedTypeExceptionWithSuccessWhenExceptionNotHaveMessage() {
        val handleUnexpectedTypeException = customerHandler
            .handleUnexpectedTypeException(UnexpectedTypeException())
        assertNotNull(handleUnexpectedTypeException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleUnexpectedTypeException.statusCode)
        assertNull(handleUnexpectedTypeException.body)
    }

    @Test
    internal fun shouldHandlePixKeyAlreadyInactivatedExceptionnWithSuccess() {
        val handleEmailNotValidException = customerHandler
            .handlePixKeyAlreadyInactivatedException(PixKeyAlreadyInactivatedException())
        assertNotNull(handleEmailNotValidException)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, handleEmailNotValidException.statusCode)
        assertEquals(CustomHandler.PIX_KEY_MESSAGE_ALREADY_INACTIVATED, handleEmailNotValidException.body?.message)
        assertEquals(PixKeyAlreadyInactivatedException::class.qualifiedName, handleEmailNotValidException.body?.error)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), handleEmailNotValidException.body?.status)
    }

}