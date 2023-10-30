package br.kotlin.consultacepservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CepRepository : JpaRepository<Cep, String> {
}