package com.itau.pix.factory

interface AbstractFactory<T> {

    fun createDefault(): T

}
