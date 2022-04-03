package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.dtos.PixKeyDto

interface PixKey {
    fun create(createPixKeyDto: CreatePixKeyDto): PixKeyDto
}
