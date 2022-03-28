package com.itau.pix.resources.v1.request

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreatePixKeyRequest(

    @field:NotNull
    var keyType: KeyType,

    @field:NotBlank
    @field:Size(min = 1, max = 77)
    var keyValue: String,

    @field:NotNull
    var accountType: AccountType,

    @field:NotNull
    var agencyNumber: Int,

    @field:NotNull
    var accountNumber: Int,

    @field:NotBlank
    @field:Size(min = 1, max = 30)
    var accountHolderName: String,

    @field:Size(min = 0, max = 77)
    var accountHolderLastName: String = ""

)