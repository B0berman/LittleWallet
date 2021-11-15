package com.bitpanda.littlewallet.model

data class Metal(
    override var name: String,
    override var symbol: String,
    override var id: String,
    override var price: Double,
    override var logo: String
) : Asset() {

    override var precision: Int = 3


}