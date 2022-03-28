package com.itau.pix.repositories

import com.itau.pix.domain.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    @Query(
        value = """
        select * from accounts ac
        where ac.agency_number = :agencyNumber
        and ac.account_number = :accountNumber
    """,
        nativeQuery = true
    )
    fun findByAgencyAndAccountNumber(
        @Param("agencyNumber") agencyNumber: Int,
        @Param("accountNumber") accountNumber: Int
    ): Optional<Account>
}