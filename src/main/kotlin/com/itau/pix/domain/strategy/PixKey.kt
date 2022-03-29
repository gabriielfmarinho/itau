package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto

interface PixKey {
    fun create(createPixKeyDto: CreatePixKeyDto)
}