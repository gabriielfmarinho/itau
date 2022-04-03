package com.itau.pix.service

import com.itau.pix.domain.dtos.PixKeyDto
import com.itau.pix.domain.entities.PixKey
import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toPixKeyDto
import com.itau.pix.repositories.PixKeyRepository
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class SavePixKeyService(
    private val pixKeyRepository: PixKeyRepository
) {

    fun create(pixKey: PixKey): PixKeyDto {
        val pixKeySaved = save(pixKey)
        return pixKeySaved.toPixKeyDto()
    }

    fun save(pixKey: PixKey): PixKey {
        log.info("saving object pix key in base, pixKey=${pixKey}")
        return pixKeyRepository.save(pixKey)
    }

    companion object {
        val log: Logger = loggerFor(SavePixKeyService::class.java)
    }
}
