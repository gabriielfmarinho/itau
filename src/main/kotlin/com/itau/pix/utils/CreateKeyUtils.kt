package com.itau.pix.utils

import br.com.caelum.stella.validation.CNPJValidator
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import com.itau.pix.domain.entities.Account
import com.itau.pix.domain.enums.TypePerson.PERSON

object CreateKeyUtils {

    private const val REGEX_TO_VALID_EMAIL: String = """[a-zA-Z0-9.\-_]*@[a-zA-Z0-9.]*"""

    private const val REGEX_TO_VALID_CPF: String = """^\d{3}\d{3}\d{3}\d{2}${'$'}"""

    private const val REGEX_TO_VALID_CNPJ: String = """^\d{2}\d{3}\d{3}\d{4}\d{2}${'$'}"""

    private const val MAX_NUMBER_PIX_KEYS_TO_PERSON: Long = 5

    private const val MAX_NUMBER_PIX_KEYS_TO_COMPANY: Long = 20

    fun isANotValidEmail(email: String): Boolean {
        val regex = Regex(REGEX_TO_VALID_EMAIL)
        return !email.matches(regex)
    }

    fun isANotValidCPF(cpf: String): Boolean {
        val regex = Regex(REGEX_TO_VALID_CPF)
        if (cpf.matches(regex)) {
            val cpfValidator = CPFValidator()
            try {
                cpfValidator.assertValid(cpf)
            } catch (invalidStateException: InvalidStateException) {
                return true
            }
            return false
        }
        return false
    }

    fun isANotValidCNPJ(cpf: String): Boolean {
        val regex = Regex(REGEX_TO_VALID_CNPJ)
        if (cpf.matches(regex)) {
            val cnpjValidator = CNPJValidator()
            try {
                cnpjValidator.assertValid(cpf)
            } catch (invalidStateException: InvalidStateException) {
                return true
            }
            return false
        }
        return false
    }

    fun hasExceededNumberOfKeysByAccount(numbersOfAccount: Long, account: Account): Boolean {
        return if (PERSON == account.client.typePerson) {
            numbersOfAccount >= MAX_NUMBER_PIX_KEYS_TO_PERSON
        } else {
            numbersOfAccount >= MAX_NUMBER_PIX_KEYS_TO_COMPANY
        }
    }
}