package com.itau.pix.resources.v1

import com.itau.pix.domain.exceptions.AccountAlreadyExistException
import com.itau.pix.domain.exceptions.ClientAlreadyRegisteredException
import com.itau.pix.domain.exceptions.CustomerDifferentFromAccountException
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
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                exception.message?.let {
                    buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.javaClass.name, it)
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
        private const val PIX_KEY_MESSAGE_ALREADY_INACTIVATED: String = "your pix key is already inactive"
        private const val PIX_KEY_MESSAGE_NOT_FOUND: String = "your pix key not found"
        private const val MESSAGE_CUSTOMER_DOES_NOT_HAVE_AN_ACCOUNT: String = "customer does not have an account"
        private const val MESSAGE_CLIENT_ALREADY_REGISTERED: String = "client already registered in the base"
        private const val MESSAGE_ACCOUNT_ALREADY_REGISTERED: String = "account already registered in the base"
    }
}
