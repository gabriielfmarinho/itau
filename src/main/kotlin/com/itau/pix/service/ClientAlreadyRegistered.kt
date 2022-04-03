package com.itau.pix.service

import com.itau.pix.domain.exceptions.ClientNotFoundException
import org.springframework.stereotype.Service

@Service
class ClientAlreadyRegistered(private val findClientService: FindClientService) {

    fun check(firstName: String, lastName: String?): Boolean {
        return try {
            findClientService.findByFirstNameAndLastName(firstName, lastName)
            true
        } catch (clientNotFoundException: ClientNotFoundException) {
            false
        }
    }
}
