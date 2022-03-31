package com.itau.pix.domain.dtos

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import java.util.*

data class PixKeyDetailsDto(
    val keyType: KeyType,

    val keyValue: String,

    val accountType: AccountType,

    val agencyNumber: Int,

    val accountNumber: Int,

    var accountHolderName: String,

    var accountHolderLastName: String
)