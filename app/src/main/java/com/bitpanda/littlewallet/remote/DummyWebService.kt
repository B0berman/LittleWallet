package com.bitpanda.littlewallet.remote

import com.bitpanda.littlewallet.model.*
import com.bitpanda.littlewallet.model.FiatWallet
import com.bitpanda.littlewallet.model.Metal
import com.bitpanda.littlewallet.model.MetalWallet

class DummyWebService {

    fun getCryptoWallets(): List<Wallet> {
        return DummyData.dummyCryptoWalletList
    }

    fun getMetalWallets(): List<Wallet> {
        return DummyData.dummyMetalWalletList
    }

    fun getFiatWallets(): List<Wallet> {
        return DummyData.dummyEURWallet
    }

    fun getCurrencyWallets(): List<CurrencyWallet> {
        return DummyData.dummyMetalWalletList.map {
            CurrencyWallet(wallet = it, currency = getCurrencyForWallet(it))
        }.sortedBy { it.wallet.balance } + DummyData.dummyEURWallet.map {
            CurrencyWallet(wallet = it, currency = getCurrencyForWallet(it))
        }.sortedBy { it.wallet.balance } + DummyData.dummyCryptoWalletList.map {
            CurrencyWallet(wallet = it, currency = getCurrencyForWallet(it))
        }.sortedBy { it.wallet.balance }
    }

    fun getAllWallets(): List<Wallet> {
        return DummyData.dummyMetalWalletList + DummyData.dummyCryptoWalletList + DummyData.dummyEURWallet
    }

    fun getCurrencyForWallet(wallet: Wallet): Currency {
        when (wallet) {
            is CryptocoinWallet -> {
                return DummyData.cryptocoins.first { it.id == wallet.cryptocoinId }
            }
            is FiatWallet -> {
                return DummyData.fiats.first { it.id == wallet.fiatId }
            }
            is MetalWallet -> {
                return DummyData.metals.first { it.id == wallet.metalId }
            }
        }
        return getCurrencies().first()
    }

    fun getCryptocoins(): List<Cryptocoin> {
        return DummyData.cryptocoins
    }

    fun getMetals(): List<Metal> {
        return DummyData.metals
    }

    fun getFiats(): List<Fiat> {
        return DummyData.fiats
    }

    fun getCurrencies(): List<Currency> {
        return DummyData.cryptocoins + DummyData.fiats + DummyData.metals
    }

}