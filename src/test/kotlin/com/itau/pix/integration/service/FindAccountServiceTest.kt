package com.itau.pix.integration.service

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import com.itau.pix.domain.exceptions.AccountNotFoundException
import com.itau.pix.integration.config.IntegrationTest
import com.itau.pix.service.FindAccountService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DBRider
@IntegrationTest
class FindAccountServiceTest(
    val findAccountService: FindAccountService
) {
    @Test
    @DataSet(value = arrayOf("datasets/create-account-to-find.yaml"))
    fun shouldFindAccountByAgencyAndAccountNumberWithSuccess() {
        val agencyNumber = 1010
        val accountNumber = 202020
        val clientFound = findAccountService.findByAgencyAndAccountNumber(agencyNumber, accountNumber)
        Assertions.assertNotNull(clientFound)
    }

    @Test
    fun shouldThrowAAccountNotFoundExceptionWhenFindAccountByAgencyAndAccountNumber() {
        val agencyNumber = 2020
        val accountNumber = 303030
        assertThrows<AccountNotFoundException> {
            findAccountService.findByAgencyAndAccountNumber(agencyNumber, accountNumber)
        }
    }
}
