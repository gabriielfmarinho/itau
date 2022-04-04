package com.itau.pix.unit.utils

import com.itau.pix.unit.config.UnitTest
import com.itau.pix.utils.CpfUtils.isANotValidCPF
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

@UnitTest
class CpfUtilsTest {

    @Test
    internal fun shouldReturnFalseWhenCpfIsValid() {
        val cpfValid = "70446478121"
        assertFalse(isANotValidCPF(cpfValid))
    }

    @Test
    internal fun shouldReturnTrueWhenCpfIsNotValid() {
        val cpfNotValid = "70325898721"
        assertTrue(isANotValidCPF(cpfNotValid))
    }

    @Test
    internal fun shouldReturnTrueWhenCpfIsNotFormatValid() {
        val cpfNotValid = "7032589872221"
        assertFalse(isANotValidCPF(cpfNotValid))
    }
}
