package com.itau.pix.service

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.dtos.PixKeyDto
import com.itau.pix.domain.strategy.CreatePixKey
import com.itau.pix.log.loggerFor
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class CreatePixKeyService(
    val createPixKey: CreatePixKey
) {

    fun create(createPixKeyDto: CreatePixKeyDto): PixKeyDto? {
        log.info("starting processing to create new key, createPixKeyDto=${createPixKeyDto}")
        return createPixKey.create(createPixKeyDto)
    }

    companion object {
        val log: Logger = loggerFor(CreatePixKeyService::class.java)
    }
}
