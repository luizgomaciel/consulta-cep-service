package br.kotlin.consultacepservice.client

import br.kotlin.consultacepservice.client.dto.AddressClient
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import org.slf4j.Logger

@Component
class ConsultaCepClient(@Autowired private var webClient: WebClient) {

    private val log: Logger = LoggerFactory.getLogger(ConsultaCepClient::class.java)

    fun findCepByNumber(cep: String): Mono<AddressClient> {
         return runBlocking {
            val res = async { createRequest(cep) }
             return@runBlocking res.await()
        }
    }

    suspend fun createRequest(cep: String): Mono<AddressClient> {
        val uri = String.format("https://viacep.com.br/ws/%s/json/", cep)
        return webClient.get()
            .uri(uri)
            .exchangeToMono {
                it.bodyToMono<AddressClient>()
            }.onErrorResume { Mono.empty() }
    }
}