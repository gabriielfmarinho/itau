package com.itau.pix.resources.v1

import com.itau.pix.domain.exceptions.PixKeyAlreadyInactivatedException
import com.itau.pix.resources.v1.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomHandler {

    @ExceptionHandler(PixKeyAlreadyInactivatedException::class)
    fun handlePixKeyAlreadyInactivatedException(exception: PixKeyAlreadyInactivatedException):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                ErrorResponse.Builder()
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                    .error(HttpStatus.UNPROCESSABLE_ENTITY.name)
                    .message(PIX_KEY_MESSAGE_ALREADY_INACTIVATED)
                    .build()
            )
    }

    companion object {
        private const val PIX_KEY_MESSAGE_ALREADY_INACTIVATED: String = "your pix key is already inactive"
    }
}