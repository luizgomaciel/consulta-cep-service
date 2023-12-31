package br.kotlin.consultacepservice.client.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressClient(
    @SerialName("cep") var cep: String,
    @SerialName("logradouro") var logradouro: String,
    @SerialName("complemento") var complemento: String,
    @SerialName("bairro") var bairro: String,
    @SerialName("localidade") var localidade: String,
    @SerialName("uf") var uf: String,
    @SerialName("ibge") var ibge: String,
    @SerialName("gia") var gia: String,
    @SerialName("ddd") var ddd: String,
    @SerialName("siafi") var siafi: String
)