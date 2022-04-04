package com.itau.pix.unit.utils

import com.itau.pix.unit.config.UnitTest
import com.itau.pix.utils.CnpjUtils.isANotValidCNPJ
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

@UnitTest
class CnpjUtilsTest {

    @Test
    internal fun shouldReturnFalseWhenCnpjIsValid() {
        val cnpjValid = "22414867000126"
        assertFalse(isANotValidCNPJ(cnpjValid))
    }

    @Test
    internal fun shouldReturnTrueWhenCnpjIsNotValid() {
        val cnpjNotValid = "22414867022126"
        assertTrue(isANotValidCNPJ(cnpjNotValid))
    }

    @Test
    internal fun shouldReturnTrueWhenCnpjIsNotFormatValid() {
        val cnpjNotValid = "22.414.867/0001-26"
        assertFalse(isANotValidCNPJ(cnpjNotValid))
    }
}
