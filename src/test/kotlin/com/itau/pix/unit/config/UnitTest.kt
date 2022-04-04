package com.itau.pix.unit.config

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@ExtendWith(MockitoExtension::class)
annotation class UnitTest
