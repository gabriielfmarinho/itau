package com.itau.pix.unit.utils

import com.itau.pix.unit.config.UnitTest
import com.itau.pix.utils.EmailUtils.isANotValidEmail
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

@UnitTest
class EmailUtilsTest {

    @Test
    internal fun shouldReturnFalseWhenEmailIsValid() {
        val emailValid = "gabriel.ferreira@gmail.com"
        assertFalse(isANotValidEmail(emailValid))
    }

    @Test
    internal fun shouldReturnTrueWhenEmailIsNotFormatValid() {
        val emailNotValid = "gabriel.com"
        assertTrue(isANotValidEmail(emailNotValid))
    }
}
