package com.itau.pix.service

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.log.loggerFor
import org.springframework.stereotype.Service

@Service
class CreatePixKeyService {

    companion object {
        val log = loggerFor(CreatePixKeyService::class.java)
    }

    fun create(toDto: CreatePixKeyDto) {

    }
}