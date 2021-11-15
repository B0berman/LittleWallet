package com.bitpanda.littlewallet.model

data class Fiat(
    override var precision : Int = 2,
    override var name : String = "",
    override var symbol : String = "",
    override var id : String = "",
    override var logo: String = ""
) : Currency {



}