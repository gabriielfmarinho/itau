package com.itau.pix.resources.v1

import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toDto
import com.itau.pix.mapper.toResponse
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import com.itau.pix.resources.v1.request.Params
import com.itau.pix.resources.v1.response.PixKeyResponse
import com.itau.pix.service.CreatePixKeyService
import com.itau.pix.service.FindPixKeyService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/pix-keys")
class PixKeyResource(
    private val createPixKeyService: CreatePixKeyService,
    private val findPixKeyService: FindPixKeyService
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

    @GetMapping("/{id}", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getById(@PathVariable id: String): ResponseEntity<PixKeyResponse> {
        log.info("receive request for get key pix, id=${id}")
        val pixKey = findPixKeyService.findById(id);
        return ResponseEntity.ok(pixKey.toResponse())
    }

    @GetMapping("/paged", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getByKeyType(params: Params): ResponseEntity<Page<PixKeyResponse>> {
        log.info("receive request for get key params, id=${params}")
        val paginatedPixKeys = findPixKeyService.findPaged(params);
        return ResponseEntity.ok(paginatedPixKeys.map { pixKey -> pixKey.toResponse() })
    }

}