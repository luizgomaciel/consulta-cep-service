package br.kotlin.consultacepservice.host

import br.kotlin.consultacepservice.host.dto.AddressResponse
import br.kotlin.consultacepservice.service.ConsultaCepService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

@Controller
class ConsultaCepGraphQL {

    @Autowired
    lateinit var service: ConsultaCepService

    @QueryMapping
    fun findCepByNumber(@Argument cep: String) : Mono<AddressResponse> {
        return service.findCepByNumber(cep)
    }

}