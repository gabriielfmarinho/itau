package com.itau.pix.factory

import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.enums.AccountType
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class AccountFactory(
    private val clientFactory: ClientFactory
): AbstractFactory<Account> {

    override fun createDefault(): Account {
        val client = clientFactory.createDefault()
        return Account(
            FAKER.random.nextLong(),
            client,
            AccountType.CURRENT,
            FAKER.random.nextInt(99, 9999),
            FAKER.random.nextInt(99, 99999999)
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}