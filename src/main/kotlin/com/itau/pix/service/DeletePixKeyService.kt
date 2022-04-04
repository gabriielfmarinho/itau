package com.itau.pix.service

import com.itau.pix.domain.entities.PixKey
import com.itau.pix.domain.exceptions.PixKeyAlreadyInactivatedException
import com.itau.pix.log.loggerFor
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class DeletePixKeyService(
    private val findPixKeyService: FindPixKeyService,
    private val savePixKeyService: SavePixKeyService
) {

    fun delete(uuid: String): PixKey {
        log.info("starting product inactivation by id, id${uuid}")
        val pixKeyFound = findPixKeyService.findEvenInactiveBydId(uuid)
        if (pixKeyFound.isInactive()) {
            throw PixKeyAlreadyInactivatedException()
        }
        pixKeyFound.inactivate()
        return savePixKeyService.save(pixKeyFound)
    }

    companion object {
        val log: Logger = loggerFor(DeletePixKeyService::class.java)
    }
}
