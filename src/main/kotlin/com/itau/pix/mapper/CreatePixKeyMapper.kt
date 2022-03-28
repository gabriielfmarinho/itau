package com.itau.pix.mapper

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.resources.v1.request.CreatePixKeyRequest

fun CreatePixKeyRequest.toDto() = CreatePixKeyDto(
    keyType = keyType,
    keyValue = keyValue,
    accountType = accountType,
    agencyNumber = agencyNumber,
    accountNumber = accountNumber,
    accountHolderName = accountHolderName,
    accountHolderLastName = accountHolderLastName
)