package com.itau.pix.service

import com.itau.pix.domain.dtos.AccountDto
import com.itau.pix.domain.dtos.CreateAccountDto
import com.itau.pix.domain.exceptions.AccountAlreadyExistException
import com.itau.pix.log.loggerFor
import com.itau.pix.mapper.toAccount
import com.itau.pix.mapper.toAccountDto
import com.itau.pix.repositories.AccountRepository
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class SaveAccountService(
    private val accountRepository: AccountRepository,
    private val findClientService: FindClientService,
    private val accountAlreadyExistService: AccountAlreadyExistService,
) {

    fun create(createAccountDto: CreateAccountDto): AccountDto {
        log.info("saving object client in base, createAccountDto=${createAccountDto}")
        if(accountAlreadyExistService.check(createAccountDto.agencyNumber, createAccountDto.agencyNumber)){
            throw AccountAlreadyExistException()
        }
        val client = findClientService.findById(createAccountDto.clientId)
        val accountSaved = accountRepository.save(createAccountDto.toAccount(client))
        return accountSaved.toAccountDto()
    }

    companion object {
        val log: Logger = loggerFor(SaveAccountService::class.java)
    }
}
