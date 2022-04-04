package com.itau.pix.factory

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreatePixKeyDtoFactory : AbstractFactory<CreatePixKeyDto> {

    override fun createDefault(): CreatePixKeyDto {
        return CreatePixKeyDto(
            KeyType.RANDOM,
            UUID.randomUUID().toString(),
            AccountType.CURRENT,
            FAKER.random.nextInt(99, 9999),
            FAKER.random.nextInt(99, 99999999),
            FAKER.name.firstName(),
            FAKER.name.lastName()
        )
    }

    fun createWith(keyType: KeyType, keyValue: String): CreatePixKeyDto {
        return CreatePixKeyDto(
            keyType,
            keyValue,
            AccountType.CURRENT,
            1010,
            202020,
            FAKER.name.firstName(),
            FAKER.name.lastName()
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}