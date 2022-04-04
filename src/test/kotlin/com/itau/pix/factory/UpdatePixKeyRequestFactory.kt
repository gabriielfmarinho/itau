package com.itau.pix.factory

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.resources.v1.request.UpdatePixKeyRequest
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class UpdatePixKeyRequestFactory : AbstractFactory<UpdatePixKeyRequest> {

    override fun createDefault(): UpdatePixKeyRequest {
        return UpdatePixKeyRequest(
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
    ): UpdatePixKeyRequest {
        return UpdatePixKeyRequest(
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