package com.bitpanda.littlewallet.repository

import com.bitpanda.littlewallet.model.*
import com.bitpanda.littlewallet.remote.DummyWebService
import com.bitpanda.littlewallet.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class Repository(private val webservice: DummyWebService) {

    suspend fun getCurrencyWallets(i: Int = 0) : Flow<DataState<List<CurrencyWallet>>> = flow {
        emit(DataState.Loading)
        delay(500)
        var wallets = webservice.getCurrencyWallets().filter { !it.wallet.deleted }
        when (i) {
            1 -> wallets = wallets.filter { it.wallet is CryptocoinWallet }
            2 -> wallets = wallets.filter { it.wallet is MetalWallet }
            3 -> wallets = wallets.filter { it.wallet is FiatWallet }
        }
        emit(DataState.Success(wallets))
    }.catch {
        emit(DataState.Error(Exception("Error loading wallets")))
    }

    suspend fun getWallets() : Flow<DataState<List<Wallet>>> = flow {
        emit(DataState.Loading)
        delay(500)
        val wallets = webservice.getAllWallets().filter { !it.deleted }
        emit(DataState.Success(wallets))
    }.catch {
        emit(DataState.Error(Exception("Error loading wallets")))
    }

    fun findBySymbol(s: String): Flow<DataState<Currency>> = flow {
        emit(DataState.Loading)
        delay(500)
        val currency = webservice.getCurrencies().first { it.symbol == s }
        emit(DataState.Success(currency))
    }.catch {
        emit(DataState.Error(Exception("Error loading currency")))
    }

}