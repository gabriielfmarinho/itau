package com.itau.pix.resources.v1

import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toCreatePixKeyDto
import com.itau.pix.mapper.toDeletePixKeyResponse
import com.itau.pix.mapper.toPixKeyResponse
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import com.itau.pix.resources.v1.request.Params
import com.itau.pix.resources.v1.response.DeletePixKeyResponse
import com.itau.pix.resources.v1.response.PixKeyResponse
import com.itau.pix.service.CreatePixKeyService
import com.itau.pix.service.DeletePixKeyService
import com.itau.pix.service.FindPixKeyService
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/pix-keys")
class PixKeyResource(
    private val createPixKeyService: CreatePixKeyService,
    private val findPixKeyService: FindPixKeyService,
    private val deletePixKeyService: DeletePixKeyService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun createPixKey(@RequestBody @Valid createPixKeyRequest: CreatePixKeyRequest) {
        log.info("receive request for create key pix, pixKeyRequest=${createPixKeyRequest}")
        createPixKeyService.create(createPixKeyRequest.toCreatePixKeyDto())
    }

    @GetMapping("/{id}", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getById(@PathVariable id: String): ResponseEntity<PixKeyResponse> {
        log.info("receive request for get key pix, id=${id}")
        val pixKey = findPixKeyService.findById(id)
        return ResponseEntity.ok(pixKey.toPixKeyResponse())
    }

    @GetMapping("/paged", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getPaged(params: Params): ResponseEntity<Page<PixKeyResponse>> {
        log.info("receive request for get pix key paged by params, params=${params}")
        val paginatedPixKeys = findPixKeyService.findPaged(params);
        return ResponseEntity.ok(paginatedPixKeys
            .map { pixKeyDetailsDto -> pixKeyDetailsDto.toPixKeyResponse() })
    }

    @DeleteMapping("/{UUID}", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun deletePixKey(@PathVariable("UUID") uuid: String): ResponseEntity<DeletePixKeyResponse> {
        log.info("receive request for delete pix key by id, uuid=${uuid}")
        val pixKey = deletePixKeyService.delete(uuid);
        return ResponseEntity.ok(pixKey.toDeletePixKeyResponse())
    }

    companion object {
        val log: Logger = loggerFor(PixKeyResource::class.java)
    }
}