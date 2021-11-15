package com.bitpanda.littlewallet.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitpanda.littlewallet.ui.adapters.CurrencyWalletsAdapter
import com.bitpanda.littlewallet.databinding.ActivityMainBinding
import com.bitpanda.littlewallet.model.CurrencyWallet
import com.bitpanda.littlewallet.util.DataState
import com.bitpanda.littlewallet.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val rvWalletsAdapter = CurrencyWalletsAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val view = binding.root

        binding.walletTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.onFilterItemSelected(0)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.onFilterItemSelected(position)
            }
        }

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setContentView(view)
        binding.rvWallets.adapter = rvWalletsAdapter
        binding.rvWallets.layoutManager = LinearLayoutManager(this)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    binding.progressMain.visibility = View.VISIBLE
                }
                is DataState.Success<List<CurrencyWallet>> -> {
                    binding.progressMain.visibility = View.GONE
                    rvWalletsAdapter.updateContent(dataState.data)
                }
                is DataState.Error -> {
                    binding.progressMain.visibility = View.GONE
                }
            }
        })
    }
}