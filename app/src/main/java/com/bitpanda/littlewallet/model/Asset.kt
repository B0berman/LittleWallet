package com.bitpanda.littlewallet.model

abstract class Asset : Currency {
    abstract var price: Double

    fun getStringPrice(): String {
        return String.format("%.${precision}f", price)
    }

}
