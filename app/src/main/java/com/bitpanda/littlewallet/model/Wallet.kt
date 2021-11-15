package com.bitpanda.littlewallet.model

import java.io.Serializable

interface Wallet : Serializable {
    var id: String
    var isDefault: Boolean
    var balance: Double
    var deleted: Boolean
    var name: String

    fun reduceBalance(amount: Double) {
        if (amount <= balance)
            balance -= amount
    }

    fun addBalance(amount: Double) {
        balance += amount
    }

    fun getStringBalance(): String {
        return String.format("%.2f", balance)
    }
}
