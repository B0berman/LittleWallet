package com.bitpanda.littlewallet.model

import java.io.Serializable

data class CurrencyWallet(
    var currency: Currency,
    var wallet: Wallet
) : Serializable
