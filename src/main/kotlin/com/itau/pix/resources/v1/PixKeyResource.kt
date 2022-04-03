package com.itau.pix.resources.v1

import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toCreatePixKeyDto
import com.itau.pix.mapper.toCreatePixKeyResponse
import com.itau.pix.mapper.toDeletePixKeyResponse
import com.itau.pix.mapper.toGetPixKeyResponse
import com.itau.pix.mapper.toUpdatePixKeyDto
import com.itau.pix.mapper.toUpdatePixKeyResponse
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import com.itau.pix.resources.v1.request.Params
import com.itau.pix.resources.v1.request.UpdatePixKeyRequest
import com.itau.pix.resources.v1.response.CreatePixKeyResponse
import com.itau.pix.resources.v1.response.DeletePixKeyResponse
import com.itau.pix.resources.v1.response.GetPixKeyResponse
import com.itau.pix.resources.v1.response.UpdatePixKeyResponse
import com.itau.pix.service.CreatePixKeyService
import com.itau.pix.service.DeletePixKeyService
import com.itau.pix.service.FindPixKeyService
import com.itau.pix.service.UpdatePixKeyService
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/pix-keys")
class PixKeyResource(
    private val createPixKeyService: CreatePixKeyService,
    private val findPixKeyService: FindPixKeyService,
    private val deletePixKeyService: DeletePixKeyService,
    private val updatePixKeyService: UpdatePixKeyService
) {

    @PostMapping(produces = [API_V1_APPLICATION_JSON_ITAU])
    fun createPixKey(@RequestBody @Valid createPixKeyRequest: CreatePixKeyRequest)
            : ResponseEntity<CreatePixKeyResponse> {
        log.info("receive request for create key pix, pixKeyRequest=${createPixKeyRequest}")
        val pixKeyCreated = createPixKeyService.create(createPixKeyRequest.toCreatePixKeyDto())
        return ResponseEntity.ok(pixKeyCreated?.toCreatePixKeyResponse())
    }

    @GetMapping("/{id}", produces = [API_V1_APPLICATION_JSON_ITAU])
    fun getById(@PathVariable id: String): ResponseEntity<GetPixKeyResponse> {
        log.info("receive request for get key pix, id=${id}")
        val pixKey = findPixKeyService.findById(id)
        return ResponseEntity.ok(pixKey.toGetPixKeyResponse())
    }

    @GetMapping("/paged", produces = [API_V1_APPLICATION_JSON_ITAU])
    fun getPaged(params: Params): ResponseEntity<Page<GetPixKeyResponse>> {
        log.info("receive request for get pix key paged by params, params=${params}")
        val paginatedPixKeys = findPixKeyService.findPaged(params);
        return ResponseEntity.ok(paginatedPixKeys
            .map { pixKeyDetailsDto -> pixKeyDetailsDto.toGetPixKeyResponse() })
    }

    @DeleteMapping("/{UUID}", produces = [API_V1_APPLICATION_JSON_ITAU])
    fun deletePixKey(@PathVariable("UUID") uuid: String): ResponseEntity<DeletePixKeyResponse> {
        log.info("receive request for delete pix key by id, uuid=${uuid}")
        val pixKey = deletePixKeyService.delete(uuid);
        return ResponseEntity.ok(pixKey.toDeletePixKeyResponse())
    }

    @PatchMapping("/{UUID}")
    fun updatePixKey(
        @PathVariable("UUID") uuid: String,
        @RequestBody @Valid updatePixKeyRequest: UpdatePixKeyRequest
    ): ResponseEntity<UpdatePixKeyResponse> {
        log.info("receive request for update pix key by id, uuid=${uuid}")
        val pixKey = updatePixKeyService.update(uuid, updatePixKeyRequest.toUpdatePixKeyDto())
        return ResponseEntity.ok(pixKey.toUpdatePixKeyResponse())
    }

    companion object {
        val log: Logger = loggerFor(PixKeyResource::class.java)
    }
}
