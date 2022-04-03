package com.itau.pix.resources.v1.request

import com.itau.pix.domain.enums.AccountType
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull

data class CreateAccountRequest(

    @field:NotNull
    val clientId: Long,

    @field:NotNull
    var accountType: AccountType,

    @field:NotNull
    @field:Digits(integer = 4, fraction = 0)
    var agencyNumber: Int,

    @field:NotNull
    @field:Digits(integer = 8, fraction = 0)
    var accountNumber: Int,

    )
