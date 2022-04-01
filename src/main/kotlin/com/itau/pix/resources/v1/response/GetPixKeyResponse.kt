package com.itau.pix.resources.v1.response

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import java.time.LocalDateTime

data class GetPixKeyResponse(

    val id: String?,

    val keyType: KeyType,

    val keyValue: String,

    val accountType: AccountType,

    val agencyNumber: Int,

    val accountNumber: Int,

    var accountHolderName: String,

    var accountHolderLastName: String,

    val dateCreate: LocalDateTime?,

    )
