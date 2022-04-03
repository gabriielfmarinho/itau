package com.itau.pix.resources.v1

import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toCreateAccountDto
import com.itau.pix.mapper.toCreateAccountResponse
import com.itau.pix.resources.v1.request.CreateAccountRequest
import com.itau.pix.resources.v1.response.CreateAccountResponse
import com.itau.pix.service.SaveAccountService
import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/accounts")
class AccountResource(
    private val saveAccountService: SaveAccountService
) {

    @PostMapping(produces = [API_V1_APPLICATION_JSON_ITAU])
    fun createAccount(@RequestBody @Valid createAccountRequest: CreateAccountRequest)
            : ResponseEntity<CreateAccountResponse> {
        log.info("receive request for create account, createClientRequest=${createAccountRequest}")
        val accountSaved = saveAccountService.create(createAccountRequest.toCreateAccountDto())
        return ResponseEntity.ok(accountSaved.toCreateAccountResponse())
    }

    companion object {
        val log: Logger = loggerFor(AccountResource::class.java)
    }
}
