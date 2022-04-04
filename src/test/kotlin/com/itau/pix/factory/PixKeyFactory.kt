package com.itau.pix.factory

import com.itau.pix.domain.entities.PixKey
import com.itau.pix.domain.enums.KeyType
import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component
import java.util.*

@Component
class PixKeyFactory(
    private val accountFactory: AccountFactory
) : AbstractFactory<PixKey> {


    override fun createDefault(): PixKey {
        val account = accountFactory.createDefault();
        return PixKey(
            UUID.randomUUID(),
            account,
            KeyType.CPF,
            "70446478121",
            false,
        )
    }

    companion object {
        private val FAKER = Faker()
    }
}