package br.kotlin.consultacepservice.repository

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "CEP")
data class Cep(
    @Id
    @UuidGenerator
    var id: String?,
    var street: String,
    var neighborhood: String,
    var uf: String,
    var cep: String
)
