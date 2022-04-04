package com.itau.pix.utils

import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException

object CpfUtils {

    private const val REGEX_TO_VALID_CPF: String = """^\d{3}\d{3}\d{3}\d{2}${'$'}"""

    private val CPF_VALIDATOR = CPFValidator()

    fun isANotValidCPF(cpf: String): Boolean {
        return isIncorrectFormat(cpf) || isNotValidCpf(cpf)
    }

    private fun isIncorrectFormat(cpf: String): Boolean {
        val regex = Regex(REGEX_TO_VALID_CPF)
        return !cpf.matches(regex)
    }

    private fun isNotValidCpf(cpf: String): Boolean {
        return try {
            CPF_VALIDATOR.assertValid(cpf)
            false
        } catch (invalidStateException: InvalidStateException) {
            true
        }
    }
}