package br.kotlin.consultacepservice.service

import br.kotlin.consultacepservice.client.ConsultaCepClient
import br.kotlin.consultacepservice.host.dto.AddressResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ConsultaCepService {

    @Autowired
    lateinit var client: ConsultaCepClient

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

}