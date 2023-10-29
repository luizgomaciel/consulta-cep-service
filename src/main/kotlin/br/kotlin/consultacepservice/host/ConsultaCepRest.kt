package br.kotlin.consultacepservice.host

import br.kotlin.consultacepservice.host.dto.AddressResponse
import br.kotlin.consultacepservice.service.ConsultaCepService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@Validated
@RequestMapping(value = ["/v1"])
class ConsultaCepRest {

    @Autowired
    lateinit var service: ConsultaCepService

    @GetMapping(value = ["/consultas/cep/{cep}"])
    fun findCepByNumber(@PathVariable cep: String) : Mono<AddressResponse> {
        return service.findCepByNumber(cep)
    }
}