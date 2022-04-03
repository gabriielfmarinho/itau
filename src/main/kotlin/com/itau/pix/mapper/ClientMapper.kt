package com.itau.pix.mapper

import com.itau.pix.domain.dtos.ClientDto
import com.itau.pix.domain.dtos.CreateClientDto
import com.itau.pix.domain.entities.Client
import com.itau.pix.resources.v1.request.CreateClientRequest
import com.itau.pix.resources.v1.response.CreateClientResponse

fun CreateClientDto.toClient() = Client(
    typePerson = typePerson,
    firstName = accountHolderName,
    lastName = if (accountHolderLastName.isNullOrBlank()) "" else accountHolderLastName,
)

fun CreateClientRequest.toCreateClientDto() = CreateClientDto(
    typePerson = typePerson,
    accountHolderName = accountHolderName,
    accountHolderLastName = accountHolderLastName,
)

fun Client.toClientDto() = ClientDto(
    id = id,
)

fun ClientDto.toCreateClientResponse() = CreateClientResponse(
    id = id,
)
