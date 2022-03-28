package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto
import org.springframework.stereotype.Component

@Component
class CreateKeyCreateKey(
    val strategies: Map<String, CreateKey>
) {

    fun execute(createPixKeyDto: CreatePixKeyDto) {
        if (hasStrategy(createPixKeyDto)) {
            throw IllegalArgumentException("The strategy does not exist")
        }
        strategies[createPixKeyDto.keyType.name]?.create(createPixKeyDto);
    }

    private fun hasStrategy(createPixKeyDto: CreatePixKeyDto) =
        !strategies.containsKey(createPixKeyDto.keyType.name)
}