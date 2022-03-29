package com.itau.pix.service

import com.itau.pix.domain.entities.PixKey
import com.itau.pix.log.loggerFor
import com.itau.pix.repositories.PixKeyRepository
import org.springframework.stereotype.Service

@Service
class SavePixKeyService(
    private val pixKeyRepository: PixKeyRepository
) {

    companion object {
        val log = loggerFor(SavePixKeyService::class.java)
    }

    fun save(pixKey: PixKey): PixKey {
        log.info("saving object pix key in base, pixKey=${pixKey}")
        return pixKeyRepository.save(pixKey);
    }
}