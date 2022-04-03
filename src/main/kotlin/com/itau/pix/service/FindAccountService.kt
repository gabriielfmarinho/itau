package com.itau.pix.service

import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.entities.PixKey
import com.itau.pix.domain.exceptions.AccountNotFoundException
import com.itau.pix.log.loggerFor
import com.itau.pix.repositories.AccountRepository
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class FindAccountService(
    val accountRepository: AccountRepository
) {

    fun findByAgencyAndAccountNumber(agencyNumber: Int, accountNumber: Int): Account {
        log.info(
            "initiating consultation in the account base by agencyNumber=${agencyNumber}," +
                    " accountNumber=${accountNumber}"
        )
        return accountRepository
            .findByAgencyAndAccountNumber(agencyNumber, accountNumber)
            .orElseThrow { AccountNotFoundException() }
    }

    fun findByAccountTypeAndAgencyNumberAndAccountNumber(
        accountType: String, agencyNumber: Int, accountNumber: Int
    ): Account {
        return accountRepository.findByAccountTypeAndAgencyNumberAndAccountNumber(
            accountType,
            agencyNumber,
            accountNumber
        )
            .orElseThrow { AccountNotFoundException() }
    }

    companion object {
        val log: Logger = loggerFor(FindAccountService::class.java)
    }
}
