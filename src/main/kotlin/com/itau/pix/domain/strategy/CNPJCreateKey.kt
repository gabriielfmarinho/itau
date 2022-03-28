package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto
import org.springframework.stereotype.Component

@Component
class CNPJCreateKey : CreateKey {

    override fun create(createPixKeyDto: CreatePixKeyDto) {
    }

}