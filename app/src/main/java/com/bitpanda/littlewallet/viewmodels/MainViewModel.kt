package com.bitpanda.littlewallet.viewmodels

import androidx.lifecycle.*
import com.bitpanda.littlewallet.model.CurrencyWallet
import com.bitpanda.littlewallet.repository.Repository
import com.bitpanda.littlewallet.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject constructor(
    private var repository: Repository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _dataState: MutableLiveData<DataState<List<CurrencyWallet>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<CurrencyWallet>>>
        get() = _dataState

    fun onFilterItemSelected(item: Int) {
        viewModelScope.launch {
            repository.getCurrencyWallets(item).collect { cw ->
                _dataState.value = cw
            }
        }
    }
}