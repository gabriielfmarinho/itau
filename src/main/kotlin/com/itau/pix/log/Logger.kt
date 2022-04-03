package com.itau.pix.log

import org.slf4j.LoggerFactory

fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)
