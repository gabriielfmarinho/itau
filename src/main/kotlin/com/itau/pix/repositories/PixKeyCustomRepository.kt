package com.itau.pix.repositories

import com.itau.pix.domain.entities.PixKey
import com.itau.pix.resources.v1.request.Params
import org.springframework.data.domain.Page
import java.util.*

interface PixKeyCustomRepository {

    fun findPaged(params: Params) : Optional<Page<PixKey>>
}