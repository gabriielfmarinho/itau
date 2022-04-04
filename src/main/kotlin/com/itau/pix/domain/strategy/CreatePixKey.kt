package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.dtos.PixKeyDto
import org.springframework.stereotype.Component

@Component
class CreatePixKey(
    val strategies: Map<String, PixKey>
) {

    fun create(createPixKeyDto: CreatePixKeyDto): PixKeyDto? {
        if (hasNoStrategy(createPixKeyDto)) {
            throw IllegalArgumentException("the strategy to ${createPixKeyDto.keyType.name} does not exist")
        }
        return strategies[createPixKeyDto.keyType.name]?.create(createPixKeyDto);
    }

    private fun hasNoStrategy(createPixKeyDto: CreatePixKeyDto) =
        !strategies.containsKey(createPixKeyDto.keyType.name)
}
