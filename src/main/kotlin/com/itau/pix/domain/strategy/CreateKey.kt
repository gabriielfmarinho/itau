package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto

interface CreateKey {
    fun create(createPixKeyDto: CreatePixKeyDto)
}