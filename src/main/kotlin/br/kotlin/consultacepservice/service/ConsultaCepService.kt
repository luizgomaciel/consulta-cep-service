package br.kotlin.consultacepservice.service

import br.kotlin.consultacepservice.client.ConsultaCepClient
import br.kotlin.consultacepservice.host.dto.AddressResponse
import br.kotlin.consultacepservice.repository.Cep
import br.kotlin.consultacepservice.repository.CepRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ConsultaCepService {

    @Autowired
    lateinit var client: ConsultaCepClient

    @Autowired
    lateinit var repository: CepRepository

    fun findCepByNumber(cep: String): Mono<AddressResponse> {
        return client.findCepByNumber(cep)
            .map {
                AddressResponse(
                    street = it.cep,
                    neighborhood = it.bairro,
                    uf = it.uf
                )
            }
    }

    fun findCepByNumberRecursivo(cep: String): Mono<AddressResponse> {
        return client.findCepByNumberRecursivo(cep)
            .map {
                AddressResponse(
                    street = it.street,
                    neighborhood = it.neighborhood,
                    uf = it.uf
                )
            }
    }

    fun findCepByNumberDb(id: String): Mono<AddressResponse> {
        return Mono.just(repository.findById(id))
            .map { it.get() }
            .map {
                AddressResponse(
                    street = it.street,
                    neighborhood = it.neighborhood,
                    uf = it.uf
                )
            }
    }

    fun insertCepByNumberDb(cep: Cep): Mono<Cep> {
        return Mono.just(repository.save(cep))
    }

}