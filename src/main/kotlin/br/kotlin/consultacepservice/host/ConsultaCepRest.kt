package br.kotlin.consultacepservice.host

import br.kotlin.consultacepservice.host.dto.AddressResponse
import br.kotlin.consultacepservice.repository.Cep
import br.kotlin.consultacepservice.service.ConsultaCepService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping(value = ["/cadastro/DB/cep"])
    fun insertCepByNumberDb(@RequestBody cep: Cep) : Mono<Cep> {
        return service.insertCepByNumberDb(cep)
    }


    @GetMapping(value = ["/consultas/cep/{id}/DB"])
    fun findCepByNumberDb(@PathVariable id: String) : Mono<AddressResponse> {
        return service.findCepByNumberDb(id)
    }

    @GetMapping(value = ["/consultas/cep/{cep}/recursivo"])
    fun findCepByNumberRecursivo(@PathVariable cep: String) : Mono<AddressResponse> {
        return service.findCepByNumberRecursivo(cep)
    }
}