package com.itau.pix.repositories

import com.itau.pix.domain.entities.PixKey
import com.itau.pix.resources.v1.request.Params
import org.springframework.data.domain.Page
import java.util.*
import javax.persistence.EntityManager

class PixKeyCustomRepositoryImpl(
    private val entityManager: EntityManager
) : PixKeyCustomRepository {

    override fun findPaged(params: Params): Optional<Page<PixKey>> {
        return Optional.empty()
    }
}