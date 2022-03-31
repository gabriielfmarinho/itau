package com.itau.pix.service

import com.itau.pix.domain.dtos.PixKeyDetailsDto
import com.itau.pix.domain.entities.PixKey
import com.itau.pix.domain.exceptions.PixKeyNotFoundException
import com.itau.pix.log.loggerFor
import com.itau.pix.repositories.PixKeyRepository
import com.itau.pix.resources.v1.request.Params
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindPixKeyService(
    val pixKeyRepository: PixKeyRepository
) {

    fun findByKeyValue(keyValue: String): PixKey {
        log.info("initiating consultation in the pix key base by keyValue=${keyValue}")
        return pixKeyRepository.findByKeyValue(keyValue)
            .orElseThrow { PixKeyNotFoundException() }
    }

    fun countPixKeyByAccount(accountId: Long) : Long {
        log.info("initiating consultation in the pix key base by accountId=${accountId}")
        return pixKeyRepository.countByAccountId(accountId)
            .orElseThrow { PixKeyNotFoundException() }
    }

    fun findById(id: String) : PixKey {
        log.info("initiating consultation in the pix key base by id=${id}")
        return pixKeyRepository.findById(UUID.fromString(id))
            .orElseThrow { PixKeyNotFoundException() }
    }

    fun findPaged(params: Params): Page<PixKeyDetailsDto> {
        log.info("initiating consultation in the pix key base by params=${params} and with pagination")
        return pixKeyRepository.findPaged(params)
            .orElseThrow { PixKeyNotFoundException() }
    }

    companion object {
        val log: Logger = loggerFor(FindPixKeyService::class.java)
    }
}