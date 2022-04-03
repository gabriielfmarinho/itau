package com.itau.pix.factory

import com.itau.pix.domain.enums.TypePerson
import com.itau.pix.resources.v1.request.CreateClientRequest
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class CreateClientRequestFactory : AbstractFactory<CreateClientRequest> {

    override fun createDefault(): CreateClientRequest {
        return CreateClientRequest(TypePerson.PERSON, FAKER.name.firstName(), FAKER.name.lastName())
    }

    fun createWith(firstName: String, lastName: String): CreateClientRequest {
        return CreateClientRequest(TypePerson.PERSON, firstName, lastName)
    }

    companion object {
        private val FAKER = Faker()
    }
}
