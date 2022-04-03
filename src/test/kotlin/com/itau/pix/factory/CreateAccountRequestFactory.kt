package com.itau.pix.factory

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.resources.v1.request.CreateAccountRequest
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class CreateAccountRequestFactory : AbstractFactory<CreateAccountRequest> {

    override fun createDefault(): CreateAccountRequest {
        return CreateAccountRequest(
            1,
            AccountType.CURRENT,
            FAKER.random.nextInt(99, 9999),
            FAKER.random.nextInt(99, 99999999)
        )
    }
    fun createWith(agencyNumber: Int, accountNumber: Int): CreateAccountRequest {
        return CreateAccountRequest(
            1,
            AccountType.CURRENT,
            agencyNumber,
            accountNumber
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}
