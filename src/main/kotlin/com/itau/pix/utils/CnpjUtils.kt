package com.itau.pix.utils

import br.com.caelum.stella.validation.CNPJValidator
import br.com.caelum.stella.validation.InvalidStateException

object CnpjUtils {

    private const val REGEX_TO_VALID_CNPJ: String = """^\d{2}\d{3}\d{3}\d{4}\d{2}${'$'}"""

    private val CNPJ_VALIDATOR = CNPJValidator()

    fun isANotValidCNPJ(cnpj: String): Boolean {
        return isIncorrectFormat(cnpj) || isNotValidCnpj(cnpj)
    }

    private fun isIncorrectFormat(cpf: String): Boolean {
        val regex = Regex(REGEX_TO_VALID_CNPJ)
        return !cpf.matches(regex)
    }

    private fun isNotValidCnpj(cnpj: String): Boolean {
        return try {
            CNPJ_VALIDATOR.assertValid(cnpj)
            false
        } catch (invalidStateException: InvalidStateException) {
            true
        }
    }

}