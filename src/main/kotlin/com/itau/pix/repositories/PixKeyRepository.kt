package com.itau.pix.repositories

import com.itau.pix.domain.entities.PixKey
import com.itau.pix.domain.enums.KeyType
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
    """,
        nativeQuery = true
    )
    fun findByKeyValue(@Param("keyValue") keyValue: String): Optional<PixKey>

    fun countByAccountId(@Param("accoundId") accountId: Long): Optional<Long>

    fun findByKeyType(@Param("keyType") keyType: KeyType): Optional<PixKey>
}