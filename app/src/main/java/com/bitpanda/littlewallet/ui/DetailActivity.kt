package com.bitpanda.littlewallet.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bitpanda.littlewallet.databinding.ActivityDetailBinding
import com.bitpanda.littlewallet.model.Asset
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

//@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val asset = intent.getSerializableExtra("asset") as Asset

        binding.asset = asset
        GlideToVectorYou.justLoadImage(this, Uri.parse(asset.logo), binding.currencyLogo)

        val view = binding.root
        setContentView(view)
    }


}