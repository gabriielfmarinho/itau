package com.itau.pix.unit.domain.entities

import com.itau.pix.factory.AccountFactory
import com.itau.pix.factory.ClientFactory
import com.itau.pix.factory.PixKeyFactory
import com.itau.pix.unit.config.UnitTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@UnitTest
class PixKeyTest {

    private lateinit var pixKeyFactory: PixKeyFactory

    @BeforeEach
    internal fun setUp() {
        val clientFactory = ClientFactory()
        val accountFactory = AccountFactory(clientFactory)
        pixKeyFactory = PixKeyFactory(accountFactory)
    }

    @Test
    internal fun shouldInactiveAPixKeyAndAddDateInactivation() {
        val pixKey = pixKeyFactory.createDefault()
        assertNull(pixKey.dateInactive)
        assertFalse(pixKey.isInactive())
        pixKey.inactivate()
        assertNotNull(pixKey.dateInactive)
        assertTrue(pixKey.isInactive())
    }
}
