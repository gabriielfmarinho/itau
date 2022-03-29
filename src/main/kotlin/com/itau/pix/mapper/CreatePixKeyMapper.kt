package com.itau.pix.mapper

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.entities.PixKey
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import com.itau.pix.resources.v1.response.PixKeyResponse

fun CreatePixKeyRequest.toDto() = CreatePixKeyDto(
    keyType = keyType,
    keyValue = keyValue,
    accountType = accountType,
    agencyNumber = agencyNumber,
    accountNumber = accountNumber,
    accountHolderName = accountHolderName,
    accountHolderLastName = accountHolderLastName
)

fun CreatePixKeyDto.toPixKey(account: Account) = PixKey(
    account = account,
    keyType = keyType,
    keyValue = keyValue
)

fun PixKey.toResponse() = PixKeyResponse(
    id = id,
    keyType = keyType,
    keyValue = keyValue,
    accountType = account.accountType,
    agencyNumber = account.agencyNumber,
    accountNumber = account.accountNumber,
    accountHolderName = account.client.firstName,
    accountHolderLastName = account.client.lastName
)