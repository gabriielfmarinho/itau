package com.itau.pix.service

import com.itau.pix.domain.exceptions.PixKeyNotFoundException
import org.springframework.stereotype.Service

@Service
class PixKeyAlreadyRegistered(private val findPixKeyService: FindPixKeyService) {

    fun check(keyValue: String): Boolean {
        try {
            findPixKeyService.findByKeyValue(keyValue)
        } catch (pixKeyNotFoundException: PixKeyNotFoundException) {
            return false
        }
        return true
    }
}
