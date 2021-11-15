package com.bitpanda.littlewallet.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.littlewallet.databinding.ItemWalletBinding
import com.bitpanda.littlewallet.model.Asset
import com.bitpanda.littlewallet.model.CurrencyWallet
import com.bitpanda.littlewallet.model.Metal
import com.bitpanda.littlewallet.ui.DetailActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import java.io.Serializable

class CurrencyWalletsAdapter(private var mWallets: List<CurrencyWallet> = listOf()) : RecyclerView.Adapter<CurrencyWalletsAdapter.ViewHolder>()
{
    private lateinit var context: Context

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemWalletBinding.inflate(inflater, parent, false)
        return ViewHolder(binding).listen { pos, _ ->
            val item = mWallets[pos]
            if (item.currency is Asset) {
                val launchNextActivity = Intent(parent.context, DetailActivity::class.java)
                launchNextActivity.putExtra("asset", item.currency as Serializable)
                parent.context.startActivity(launchNextActivity)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mWallets[position])


    override fun getItemCount(): Int {
        return mWallets.size
    }

    fun updateContent(newItems: List<CurrencyWallet>) {
        mWallets = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemWalletBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CurrencyWallet) {
            binding.item = item
            GlideToVectorYou.justLoadImage(context as AppCompatActivity, Uri.parse(item.currency.logo), binding.currencyLogo)
            if (item.currency is Metal) {
                binding.currencyName.visibility = View.VISIBLE
                binding.currencyName.text = item.currency.name
            }
        }
    }
}