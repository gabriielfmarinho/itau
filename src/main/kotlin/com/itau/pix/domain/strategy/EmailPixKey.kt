package com.itau.pix.domain.strategy

import com.itau.pix.domain.dtos.CreatePixKeyDto
import com.itau.pix.domain.dtos.PixKeyDto
import com.itau.pix.domain.exceptions.EmailNotValidException
import com.itau.pix.domain.exceptions.EqualKeyException
import com.itau.pix.domain.exceptions.ExceededNumbersOfKeysException
import com.itau.pix.mapper.toPixKey
import com.itau.pix.service.FindAccountService
import com.itau.pix.service.FindPixKeyService
import com.itau.pix.service.PixKeyAlreadyRegistered
import com.itau.pix.service.SavePixKeyService
import com.itau.pix.utils.CreateKeyUtils.hasExceededNumberOfKeysByAccount
import com.itau.pix.utils.CreateKeyUtils.isANotValidEmail
import org.springframework.stereotype.Component

@Component("EMAIL")
class EmailPixKey(
    private val findPixKeyService: FindPixKeyService,
    private val findAccountService: FindAccountService,
    private val savePixKeyService: SavePixKeyService,
    private val pixKeyAlreadyRegistered: PixKeyAlreadyRegistered
) : PixKey {

    override fun create(createPixKeyDto: CreatePixKeyDto): PixKeyDto {

        if (isANotValidEmail(createPixKeyDto.keyValue)) {
            throw EmailNotValidException()
        }

        if (pixKeyAlreadyRegistered.check(createPixKeyDto.keyValue)) {
            throw EqualKeyException()
        }

        val account = findAccountService
            .findByAgencyAndAccountNumber(createPixKeyDto.agencyNumber, createPixKeyDto.accountNumber)

        val numbersOfAccount = findPixKeyService.countPixKeyByAccount(account.id)

        if (hasExceededNumberOfKeysByAccount(numbersOfAccount, account)) {
            throw ExceededNumbersOfKeysException()
        }

        return savePixKeyService.create(createPixKeyDto.toPixKey(account));
    }
}
