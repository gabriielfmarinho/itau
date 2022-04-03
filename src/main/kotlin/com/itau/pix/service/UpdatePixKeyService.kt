package com.itau.pix.service

import com.itau.pix.domain.dtos.UpdatePixKeyDto
import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.entities.PixKey
import com.itau.pix.domain.exceptions.CustomerDifferentFromAccountException
import com.itau.pix.domain.exceptions.PixKeyAlreadyInactivatedException
import org.springframework.stereotype.Service

@Service
class UpdatePixKeyService(
    private val findPixKeyService: FindPixKeyService,
    private val findAccountService: FindAccountService,
    private val savePixKeyService: SavePixKeyService
) {

    fun update(uuid: String, updatePixKeyDto: UpdatePixKeyDto): PixKey {
        val pixKeyToUpdate = findPixKeyService.findEntityById(uuid)

        if (pixKeyToUpdate.isInactive()) {
            throw PixKeyAlreadyInactivatedException()
        }

        val accountToUpdate =
            findAccountService.findByAccountTypeAndAgencyNumberAndAccountNumber(
                updatePixKeyDto.accountType.name,
                updatePixKeyDto.agencyNumber,
                updatePixKeyDto.accountNumber
            )

        if (isTheAccountCustomer(accountToUpdate, updatePixKeyDto)) {
            throw CustomerDifferentFromAccountException()
        }

        val pixKeyToSave = pixKeyToUpdate.apply {
            account = accountToUpdate
        }

        return savePixKeyService.save(pixKeyToSave)
    }

    private fun isTheAccountCustomer(
        accountToUpdate: Account,
        updatePixKeyDto: UpdatePixKeyDto
    ): Boolean {
        return ("${accountToUpdate.client.firstName} ${accountToUpdate.client.lastName}"
                != "${updatePixKeyDto.accountHolderName} ${updatePixKeyDto.accountHolderLastName}")
    }

}
