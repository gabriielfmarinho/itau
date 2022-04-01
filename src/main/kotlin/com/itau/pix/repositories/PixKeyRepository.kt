package com.itau.pix.repositories

import com.itau.pix.domain.entities.PixKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PixKeyRepository : JpaRepository<PixKey, UUID>, PixKeyCustomRepository {

    @Query(
        value = """
        select * from keys_pix kp
        where kp.key_value = :keyValue
        and kp.inactive = false
    """,
        nativeQuery = true
    )
    fun findByKeyValue(@Param("keyValue") keyValue: String): Optional<PixKey>

    @Query(
        value = """
        select count(*) from keys_pix kp
        where kp.account_id = :accountId
        and kp.inactive = false
    """,
        nativeQuery = true
    )
    fun countByAccountId(@Param("accountId") accountId: Long): Optional<Long>

    @Query(
        value = """
        select * from keys_pix kp
        where kp.id = :id
        and kp.inactive = false
    """,
        nativeQuery = true
    )
    fun findById(@Param("id") id: String): Optional<PixKey>
}