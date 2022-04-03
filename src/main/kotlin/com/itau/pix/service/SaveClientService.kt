package com.itau.pix.service

import com.itau.pix.domain.dtos.ClientDto
import com.itau.pix.domain.dtos.CreateClientDto
import com.itau.pix.domain.exceptions.ClientAlreadyRegisteredException
import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toClient
import com.itau.pix.mapper.toClientDto
import com.itau.pix.repositories.ClientRepository
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class SaveClientService(
    private val clientRepository: ClientRepository,
    private val clientAlreadyRegistered: ClientAlreadyRegistered
) {

    fun create(createClientDto: CreateClientDto): ClientDto {
        log.info("saving object client in base, createClientDto=${createClientDto}")
        if(clientAlreadyRegistered.check(createClientDto.accountHolderName, createClientDto.accountHolderLastName)) {
            throw ClientAlreadyRegisteredException()
        }
        val clientSaved = clientRepository.save(createClientDto.toClient())
        return clientSaved.toClientDto()
    }

    companion object {
        val log: Logger = loggerFor(SaveClientService::class.java)
    }
}
