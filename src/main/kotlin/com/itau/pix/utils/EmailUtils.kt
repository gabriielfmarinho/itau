package com.itau.pix.utils

object EmailUtils {

    private const val REGEX_TO_VALID_EMAIL: String = """[a-zA-Z0-9.\-_]*@[a-zA-Z0-9.]*"""

    fun isANotValidEmail(email: String): Boolean {
        val regex = Regex(REGEX_TO_VALID_EMAIL)
        return !email.matches(regex)
    }

}
