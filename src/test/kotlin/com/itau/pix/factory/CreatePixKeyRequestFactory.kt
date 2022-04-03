package com.itau.pix.factory

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import com.itau.pix.resources.v1.request.CreatePixKeyRequest
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreatePixKeyRequestFactory: AbstractFactory<CreatePixKeyRequest> {

    override fun createDefault(): CreatePixKeyRequest {
        return CreatePixKeyRequest(KeyType.RANDOM,
            UUID.randomUUID().toString(),
            AccountType.CURRENT,
            FAKER.random.nextInt(99, 9999),
            FAKER.random.nextInt(99, 99999999),
            FAKER.name.firstName(),
            FAKER.name.lastName()
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}
