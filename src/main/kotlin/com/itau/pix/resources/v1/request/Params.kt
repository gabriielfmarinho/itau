package com.itau.pix.resources.v1.request

import com.itau.pix.domain.enums.KeyType

data class Params(
    val page: Int = 0,

    val size: Int = 20,

    val keyType: KeyType?,

    val agencyNumber: Int?,

    val accountNumber: Int?,
)
