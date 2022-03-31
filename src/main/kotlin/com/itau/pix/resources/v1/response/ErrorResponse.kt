package com.itau.pix.resources.v1.response

class ErrorResponse private constructor(
    builder: ErrorResponse.Builder
) {

    val status: Int?

    val error: String?

    val message: String?

    init {
        status = builder.status
        error = builder.error
        message = builder.message
    }

    class Builder {

        var status: Int? = null
            private set

        var error: String? = null
            private set

        var message: String? = null
            private set

        fun status(status: Int) = apply { this.status = status }

        fun error(status: String) = apply { this.error = error }

        fun message(message: String) = apply { this.message = message }

        fun build() = ErrorResponse(this)
    }
}
