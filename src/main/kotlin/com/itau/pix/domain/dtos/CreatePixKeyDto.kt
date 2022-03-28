package com.itau.pix.domain.dtos

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType

data class CreatePixKeyDto(
    val keyType: KeyType,
    val keyValue: String,
    val accountType: AccountType,
    val agencyNumber: Int,
    val accountNumber: Int,
    val accountHolderName: String,
    val accountHolderLastName: String
)