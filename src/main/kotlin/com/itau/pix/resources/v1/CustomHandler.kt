package com.itau.pix.resources.v1

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
import com.itau.pix.resources.v1.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.UnexpectedTypeException


@ControllerAdvice
class CustomHandler {

    @ExceptionHandler(PixKeyAlreadyInactivatedException::class)
    fun handlePixKeyAlreadyInactivatedException(exception: PixKeyAlreadyInactivatedException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    PIX_KEY_MESSAGE_ALREADY_INACTIVATED
                )
            )
    }

    @ExceptionHandler(PixKeyNotFoundException::class)
    fun handlePixKeyNotFoundException(exception: PixKeyNotFoundException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                buildErrorResponse(HttpStatus.NOT_FOUND, exception.javaClass.name, PIX_KEY_MESSAGE_NOT_FOUND)
            )
    }

    @ExceptionHandler(ClientNotFoundException::class)
    fun handleClientNotFoundException(exception: ClientNotFoundException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                buildErrorResponse(HttpStatus.NOT_FOUND, exception.javaClass.name, CLIENT_MESSAGE_NOT_FOUND)
            )
    }

    @ExceptionHandler(AccountNotFoundException::class)
    fun handleAccountNotFoundException(exception: AccountNotFoundException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                buildErrorResponse(HttpStatus.NOT_FOUND, exception.javaClass.name, ACCOUNT_MESSAGE_NOT_FOUND)
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {
        val messages = exception.bindingResult.fieldErrors
            .map { violation -> getConstraintMessage(violation) }
            .toList()
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception.javaClass.name, messages))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                exception.message?.let {
                    buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.javaClass.name, it)
                }
            )
    }

    @ExceptionHandler(UnexpectedTypeException::class)
    fun handleUnexpectedTypeException(exception: UnexpectedTypeException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                exception.message?.let {
                    buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception.javaClass.name, it)
                }
            )
    }

    @ExceptionHandler(ClientAlreadyRegisteredException::class)
    fun handleClientAlreadyRegisteredException(exception: ClientAlreadyRegisteredException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_CLIENT_ALREADY_REGISTERED
                )
            )
    }

    @ExceptionHandler(CPFNotValidException::class)
    fun handleCPFNotValidException(exception: CPFNotValidException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_CPF_NOT_VALID
                )
            )
    }

    @ExceptionHandler(CNPJNotValidException::class)
    fun handleCNPJNotValidException(exception: CNPJNotValidException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_CNPJ_NOT_VALID
                )
            )
    }

    @ExceptionHandler(EmailNotValidException::class)
    fun handleEmailNotValidException(exception: EmailNotValidException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_EMAIL_NOT_VALID
                )
            )
    }

    @ExceptionHandler(EqualKeyException::class)
    fun handleEqualKeyException(exception: EqualKeyException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_PIX_KEY_ALREADY_REGISTERED
                )
            )
    }

    @ExceptionHandler(ExceededNumbersOfKeysException::class)
    fun handleExceededNumbersOfKeysException(exception: ExceededNumbersOfKeysException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_EXCEEDED_NUMBER_OF_KEYS
                )
            )
    }

    @ExceptionHandler(AccountAlreadyExistException::class)
    fun handleAccountAlreadyExistException(exception: AccountAlreadyExistException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_ACCOUNT_ALREADY_REGISTERED
                )
            )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                exception.message?.let {
                    buildErrorResponse(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        exception.javaClass.name,
                        it
                    )
                }
            )
    }

    @ExceptionHandler(CustomerDifferentFromAccountException::class)
    fun handleCustomerDifferentFromAccountException(exception: CustomerDifferentFromAccountException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                buildErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    exception.javaClass.name,
                    MESSAGE_CUSTOMER_DOES_NOT_HAVE_AN_ACCOUNT
                )
            )
    }

    private fun getConstraintMessage(field: FieldError): String {
        return "${field.field} ${field.defaultMessage}"
    }

    private fun buildErrorResponse(status: HttpStatus, error: String, message: String): ErrorResponse {
        return ErrorResponse.Builder()
            .status(status.value())
            .error(error)
            .message(message)
            .build()
    }

    private fun buildErrorResponse(status: HttpStatus, error: String, messages: List<String>): ErrorResponse {
        return ErrorResponse.Builder()
            .status(status.value())
            .error(error)
            .messages(messages)
            .build()
    }

    companion object {
        const val PIX_KEY_MESSAGE_ALREADY_INACTIVATED: String = "your pix key is already inactive"
        const val PIX_KEY_MESSAGE_NOT_FOUND: String = "your pix key not found"
        const val CLIENT_MESSAGE_NOT_FOUND: String = "your client informed not found"
        const val ACCOUNT_MESSAGE_NOT_FOUND: String = "your account not found"
        const val MESSAGE_CUSTOMER_DOES_NOT_HAVE_AN_ACCOUNT: String = "customer does not have an account"
        const val MESSAGE_CLIENT_ALREADY_REGISTERED: String = "client already registered in the base"
        const val MESSAGE_EXCEEDED_NUMBER_OF_KEYS: String = "user exceeded number of keys"
        const val MESSAGE_PIX_KEY_ALREADY_REGISTERED: String = "pix key already registered in the base"
        const val MESSAGE_ACCOUNT_ALREADY_REGISTERED: String = "account already registered in the base"
        const val MESSAGE_CPF_NOT_VALID: String = "cpf is not valid"
        const val MESSAGE_CNPJ_NOT_VALID: String = "cnpj is not valid"
        const val MESSAGE_EMAIL_NOT_VALID: String = "email is not valid"
    }
}
