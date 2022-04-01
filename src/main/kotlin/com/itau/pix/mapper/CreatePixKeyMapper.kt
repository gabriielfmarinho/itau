package com.itau.pix.mapper

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.dtos.PixKeyDetailsDto
import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.entities.PixKey
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import com.itau.pix.resources.v1.response.DeletePixKeyResponse
import com.itau.pix.resources.v1.response.GetPixKeyResponse
import java.util.*

fun CreatePixKeyRequest.toCreatePixKeyDto() = CreatePixKeyDto(
    keyType = keyType,
    keyValue = keyValue,
    accountType = accountType,
    agencyNumber = agencyNumber,
    accountNumber = accountNumber,
    accountHolderName = accountHolderName,
    accountHolderLastName = ""
)

fun CreatePixKeyDto.toPixKey(account: Account) = PixKey(
    account = account,
    keyType = keyType,
    keyValue = keyValue,
    inactive = false
)

fun PixKey.toGetPixKeyResponse() = GetPixKeyResponse(
    id  = id.toString(),
    keyType = keyType,
    keyValue = keyValue,
    accountType = account.accountType,
    agencyNumber = account.agencyNumber,
    accountNumber = account.accountNumber,
    accountHolderName = account.client.firstName,
    accountHolderLastName = account.client.lastName,
    dateCreate = dateCreate,
)

fun PixKeyDetailsDto.toGetPixKeyResponse() = GetPixKeyResponse(
    id = id,
    keyType = keyType,
    keyValue = keyValue,
    accountType = accountType,
    agencyNumber = agencyNumber,
    accountNumber = accountNumber,
    accountHolderName = accountHolderName,
    accountHolderLastName = accountHolderLastName,
    dateCreate = dateCreate,
)

fun PixKey.toDeletePixKeyResponse() = DeletePixKeyResponse(
    keyType = keyType,
    keyValue = keyValue,
    accountType = account.accountType,
    agencyNumber = account.agencyNumber,
    accountNumber = account.accountNumber,
    accountHolderName = account.client.firstName,
    accountHolderLastName = account.client.lastName,
    dateInactive = dateInactive,
    dateCreate = dateCreate,
)