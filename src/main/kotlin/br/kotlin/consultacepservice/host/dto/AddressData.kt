package br.kotlin.consultacepservice.host.dto

data class AddressData(
    var neighborhood: String,
    var uf: String,
    var street: String,
    var list: List<AddressData>
)
