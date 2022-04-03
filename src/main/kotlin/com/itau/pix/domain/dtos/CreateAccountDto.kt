package com.itau.pix.domain.dtos

import com.itau.pix.domain.enums.AccountType

data class CreateAccountDto(

    val clientId: Long,

    val accountType: AccountType,

    val agencyNumber: Int,

    val accountNumber: Int,

    )
