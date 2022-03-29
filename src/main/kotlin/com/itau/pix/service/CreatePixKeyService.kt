package com.itau.pix.service

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.strategy.CreatePixKey
import com.itau.pix.log.loggerFor
import org.springframework.stereotype.Service

@Service
class CreatePixKeyService (
    val createPixKey: CreatePixKey
        ) {

    companion object {
        val log = loggerFor(CreatePixKeyService::class.java)
    }

    fun create(createPixKeyDto: CreatePixKeyDto) {
        log.info("starting processing to create new key, createPixKeyDto=${createPixKeyDto}")
        createPixKey.create(createPixKeyDto)
    }
}