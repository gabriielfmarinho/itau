package com.itau.pix.service

import com.itau.pix.domain.entities.Client
import com.itau.pix.domain.exceptions.ClientNotFoundException
import com.itau.pix.log.loggerFor
import com.itau.pix.repositories.ClientRepository
import org.springframework.stereotype.Service

@Service
class FindClientService(
    private val clientRepository: ClientRepository
) {

    companion object {
        val log = loggerFor(FindClientService::class.java)
    }

    fun findClientByFirstName(firstName: String): Client {
        log.info("initiating consultation in the customer base by firstName=${firstName}")
        return clientRepository
            .findByFirstName(firstName)
            .orElseThrow { ClientNotFoundException() }
    }
}