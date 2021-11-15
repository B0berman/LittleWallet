package com.bitpanda.littlewallet.model

import java.io.Serializable

interface Currency: Serializable {
    var precision : Int
    var name : String
    var symbol : String
    var id : String
    var logo: String
}
