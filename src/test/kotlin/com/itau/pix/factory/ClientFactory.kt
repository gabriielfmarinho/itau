package com.itau.pix.factory

import com.itau.pix.domain.entities.Client
import com.itau.pix.domain.enums.TypePerson
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class ClientFactory : AbstractFactory<Client> {

    override fun createDefault(): Client {
        return Client(
            FAKER.random.nextLong(),
            TypePerson.PERSON,
            FAKER.name.firstName(),
            FAKER.name.lastName(),
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}
