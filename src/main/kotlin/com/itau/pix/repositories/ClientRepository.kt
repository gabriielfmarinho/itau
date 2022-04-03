package com.itau.pix.repositories

import com.itau.pix.domain.entities.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<Client, Long> {

    @Query(
        value = """
        select * from clients c
        where c.first_name = :firstName
    """,
        nativeQuery = true
    )
    fun findByFirstName(@Param("firstName") firstName: String): Optional<Client>

    fun findByFirstNameAndLastName(@Param("firstName") firstName: String,
                        @Param("lastName") lastName: String?): Optional<Client>
}
