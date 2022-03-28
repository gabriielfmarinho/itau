package com.itau.pix.resources.v1

import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toDto
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import com.itau.pix.service.CreatePixKeyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/pix-keys")
class PixKeyResource(
    private val createPixKeyService: CreatePixKeyService
) {

    companion object {
        val log = loggerFor(PixKeyResource::class.java)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun createPixKey(@RequestBody @Valid createPixKeyRequest: CreatePixKeyRequest) {
        log.info("receive request for create key pix, pixKeyRequest=${createPixKeyRequest}")
        createPixKeyService.create(createPixKeyRequest.toDto())
    }
}