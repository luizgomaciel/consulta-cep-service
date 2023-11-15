package br.kotlin.consultacepservice.service

import br.kotlin.consultacepservice.client.ConsultaCepClient
import br.kotlin.consultacepservice.host.dto.AddressData
import br.kotlin.consultacepservice.host.dto.AddressResponse
import br.kotlin.consultacepservice.repository.Cep
import br.kotlin.consultacepservice.repository.CepRepository
import io.reactivex.Observable
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
                    street = it.logradouro,
                    neighborhood = it.bairro,
                    uf = it.uf,
                    list = listOf(AddressData(
                        street = it.logradouro,
                        neighborhood = it.bairro,
                        uf = it.uf,
                        list = listOf()
                    ))
                )
            }
    }

    fun findCepByNumberKtor(cep: String): AddressResponse {
        return client.findCepByNumberKtor(cep)
            .let {
                AddressResponse(
                    street = it.logradouro,
                    neighborhood = it.bairro,
                    uf = it.uf,
                    list = listOf(AddressData(
                        street = it.logradouro,
                        neighborhood = it.bairro,
                        uf = it.uf,
                        list = listOf()
                    ))
                )
            }
    }

    fun findCepByNumberRecursivo(cep: String): Mono<AddressResponse> {
        return client.findCepByNumberRecursivo(cep)
            .map {
                AddressResponse(
                    street = it.street,
                    neighborhood = it.neighborhood,
                    uf = it.uf,
                    list = it.list
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
                    uf = it.uf,
                    list = listOf()
                )
            }
    }

    fun insertCepByNumberDb(cep: Cep): Mono<Cep> {
        return Mono.just(repository.save(cep))
    }

    fun findCepByNumberFeign(cep: String): AddressResponse {
        return client.findCepByNumberFeign(cep)
            .let {
                AddressResponse(
                    street = it.logradouro,
                    neighborhood = it.bairro,
                    uf = it.uf,
                    list = listOf(AddressData(
                        street = it.logradouro,
                        neighborhood = it.bairro,
                        uf = it.uf,
                        list = listOf()
                    ))
                )
            }
    }

    fun findCepByNumberRetrofit(cep: String): AddressResponse {
        return client.findCepByNumberRetrofit(cep)
            .let {
                AddressResponse(
                    street = it.logradouro,
                    neighborhood = it.bairro,
                    uf = it.uf,
                    list = listOf(AddressData(
                        street = it.logradouro,
                        neighborhood = it.bairro,
                        uf = it.uf,
                        list = listOf()
                    ))
                )
            }
    }

    fun findCepByNumberRX(cep: String): Observable<AddressResponse> {
        return client.findCepByNumberRX(cep)
            .map {
                AddressResponse(
                    street = it.logradouro,
                    neighborhood = it.bairro,
                    uf = it.uf,
                    list = listOf(AddressData(
                        street = it.logradouro,
                        neighborhood = it.bairro,
                        uf = it.uf,
                        list = listOf()
                    ))
                )
            }
    }

}