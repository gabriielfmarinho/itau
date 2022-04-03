package com.itau.pix.domain.dtos

import com.itau.pix.domain.enums.AccountType

data class UpdatePixKeyDto(

    var accountType: AccountType,

    var agencyNumber: Int,

    var accountNumber: Int,

    var accountHolderName: String,

    var accountHolderLastName: String?
)
