package com.bitpanda.littlewallet

import org.junit.Test

import org.junit.Assert.*
import app.cash.turbine.test
import com.bitpanda.littlewallet.remote.DummyData
import com.bitpanda.littlewallet.remote.DummyWebService
import com.bitpanda.littlewallet.repository.Repository
import com.bitpanda.littlewallet.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule

@ExperimentalCoroutinesApi
class RepositoryUnitTest() {
    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    private val repository = Repository(DummyWebService())

    @Test
    fun getWallets_isCorrect() = runBlocking {
        repository.getWallets().test {
            assertTrue(awaitItem() is DataState.Loading)
            assertTrue(awaitItem() is DataState.Success)
            awaitComplete()
        }
    }

    @Test
    fun findBySymbol_isCorrect() = runBlocking {
        repository.findBySymbol("BTC").test {
            assertTrue(awaitItem() is DataState.Loading)
            val successItem = awaitItem()
            assertEquals(successItem as DataState.Success,
                DataState.Success(DummyData.cryptocoins.first { it.symbol == "BTC" }))
            awaitComplete()
        }
    }
}