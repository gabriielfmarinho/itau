package com.itau.pix.domain.dtos

import com.itau.pix.domain.enums.TypePerson

data class CreateClientDto(
    val typePerson: TypePerson,

    val accountHolderName: String,

    val accountHolderLastName: String?,
)
