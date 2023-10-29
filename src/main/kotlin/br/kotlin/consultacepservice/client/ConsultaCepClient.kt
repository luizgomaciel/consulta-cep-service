package br.kotlin.consultacepservice.client

import br.kotlin.consultacepservice.client.dto.AddressClient
import br.kotlin.consultacepservice.host.dto.AddressData
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import org.slf4j.Logger
import org.springframework.graphql.client.HttpGraphQlClient

@Component
class ConsultaCepClient(@Autowired private var webClient: WebClient) {

    private val log: Logger = LoggerFactory.getLogger(ConsultaCepClient::class.java)

    fun findCepByNumber(cep: String): Mono<AddressClient> {
        return runBlocking {
            val res = async { createRequest(cep) }
            return@runBlocking res.await()
        }
    }

    fun findCepByNumberRecursivo(cep: String): Mono<AddressData> {
        return runBlocking {
            val res = async { createRequestRecursivo(cep) }
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

    suspend fun createRequestRecursivo(cep: String): Mono<AddressData> {
        val graphQlClient: HttpGraphQlClient = HttpGraphQlClient.builder(webClient)
            .url("http://localhost:8080/graphql")
            .build()

        var param: String = String.format("%s", cep)

        val document: String = """query FindCepByNumber {
                findCepByNumber(cep: """" + param + """") {
                    neighborhood
                    uf
                    street
                }
            }
            """

        return graphQlClient.document(document)
            .execute()
            .map { it.getData<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>>() }
            .map { it?.get("findCepByNumber") }
            .map {
                it?.let { itt ->
                    AddressData(
                        street = itt.get("street").toString(),
                        neighborhood = itt.get("neighborhood").toString(),
                        uf = itt.get("uf").toString()
                    )
                } ?: AddressData(
                    street = "",
                    neighborhood = "",
                    uf = ""
                )
            }

    }
}
