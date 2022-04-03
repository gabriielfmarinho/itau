package com.itau.pix.service

import com.itau.pix.domain.exceptions.AccountNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountAlreadyExistService(
    private val findAccountService: FindAccountService
) {

    fun check(agencyNumber: Int, accountNumber: Int): Boolean {
        return try {
            findAccountService.findByAgencyAndAccountNumber(agencyNumber, accountNumber)
            true
        } catch (accountNotFoundException: AccountNotFoundException) {
            false
        }
    }
}
