package com.itau.pix.mapper

import com.itau.pix.domain.dtos.AccountDto
import com.itau.pix.domain.dtos.CreateAccountDto
import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.entities.Client
import com.itau.pix.resources.v1.request.CreateAccountRequest
import com.itau.pix.resources.v1.response.CreateAccountResponse

fun CreateAccountDto.toAccount(client: Client) = Account(
    client = client,
    accountType = accountType,
    accountNumber = accountNumber,
    agencyNumber = agencyNumber,
)

fun CreateAccountRequest.toCreateAccountDto() = CreateAccountDto(
    clientId = clientId,
    accountType = accountType,
    accountNumber = accountNumber,
    agencyNumber = agencyNumber,
)

fun Account.toAccountDto() = AccountDto(
    id = id,
)

fun AccountDto.toCreateAccountResponse() = CreateAccountResponse(
    id = id,
)
