package com.itau.pix.factory

import com.itau.pix.domain.dtos.UpdatePixKeyDto
import com.itau.pix.domain.enums.AccountType
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class UpdatePixKeyDtoFactory : AbstractFactory<UpdatePixKeyDto> {

    override fun createDefault(): UpdatePixKeyDto {
        return UpdatePixKeyDto(
            AccountType.SAVIGNS,
            FAKER.random.nextInt(99, 9999),
            FAKER.random.nextInt(99, 99999999),
            FAKER.name.firstName(),
            FAKER.name.lastName()
        )
    }

    fun createWith(
        agencyNumber: Int,
        accountNumber: Int,
        firstName: String,
        lastName: String
    ): UpdatePixKeyDto {
        return UpdatePixKeyDto(
            AccountType.SAVIGNS,
            agencyNumber,
            accountNumber,
            firstName,
            lastName
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}