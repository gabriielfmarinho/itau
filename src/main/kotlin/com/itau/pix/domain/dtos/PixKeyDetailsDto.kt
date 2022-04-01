package com.itau.pix.domain.dtos

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import java.time.LocalDateTime

data class PixKeyDetailsDto(

    val id: String,

    val keyType: KeyType,

    val keyValue: String,

    val accountType: AccountType,

    val agencyNumber: Int,

    val accountNumber: Int,

    var accountHolderName: String,

    var accountHolderLastName: String,

    val dateCreate: LocalDateTime?

)