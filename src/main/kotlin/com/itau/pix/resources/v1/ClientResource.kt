package com.itau.pix.resources.v1

import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toCreateClientDto
import com.itau.pix.mapper.toCreateClientResponse
import com.itau.pix.resources.v1.request.CreateClientRequest
import com.itau.pix.resources.v1.response.CreateClientResponse
import com.itau.pix.service.SaveClientService
import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/clients")
class ClientResource(
    private val saveClientService: SaveClientService
) {

    @PostMapping(produces = [API_V1_APPLICATION_JSON_ITAU])
    fun createClient(@RequestBody @Valid createClientRequest: CreateClientRequest)
            : ResponseEntity<CreateClientResponse> {
        log.info("receive request for create client, createClientRequest=${createClientRequest}")
        val clientSaved = saveClientService.create(createClientRequest.toCreateClientDto())
        return ResponseEntity.ok(clientSaved.toCreateClientResponse())
    }

    companion object {
        val log: Logger = loggerFor(ClientResource::class.java)
    }
}
