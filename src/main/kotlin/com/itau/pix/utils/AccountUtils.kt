package com.itau.pix.utils

import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.enums.TypePerson

object AccountUtils {

    private const val MAX_NUMBER_PIX_KEYS_TO_PERSON: Long = 5

    private const val MAX_NUMBER_PIX_KEYS_TO_COMPANY: Long = 20

    fun hasExceededNumberOfKeysByAccount(numbersOfAccount: Long, account: Account): Boolean {
        return if (TypePerson.PERSON == account.client.typePerson) {
            numbersOfAccount >= MAX_NUMBER_PIX_KEYS_TO_PERSON
        } else {
            numbersOfAccount >= MAX_NUMBER_PIX_KEYS_TO_COMPANY
        }
    }
}