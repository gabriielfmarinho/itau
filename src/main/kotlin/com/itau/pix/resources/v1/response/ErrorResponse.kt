package com.itau.pix.resources.v1.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse private constructor(
    builder: ErrorResponse.Builder
) {

    val status: Int?

    val error: String?

    val message: String?
    
    val messages: List<String>?

    init {
        status = builder.status
        error = builder.error
        message = builder.message
        messages = builder.messages
    }

    class Builder {

        var status: Int? = null
            private set

        var error: String? = null
            private set

        var message: String? = null
            private set

        var messages: List<String>? = null
            private set

        fun status(status: Int) = apply { this.status = status }

        fun error(error: String) = apply { this.error = error }

        fun message(message: String) = apply { this.message = message }

        fun messages(messages: List<String>?) = apply { this.messages = messages }

        fun build() = ErrorResponse(this)
    }
}
