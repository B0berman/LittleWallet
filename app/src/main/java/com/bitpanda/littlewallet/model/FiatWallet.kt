package com.bitpanda.littlewallet.model

class FiatWallet(
    override var id: String,
    var fiatId: String,
    override var isDefault: Boolean,
    override var balance: Double,
    override var deleted: Boolean,
    override var name: String) : Wallet {

    override fun reduceBalance(amount: Double) {
    }

    override fun addBalance(amount: Double) {
    }

}