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
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

@Component
class ConsultaCepClient(
    @Autowired private var webClient: WebClient,
    @Autowired private var cepFeignClient: CepFeignClient
) {

    private val log: Logger = LoggerFactory.getLogger(ConsultaCepClient::class.java)

    fun findCepByNumber(cep: String): Mono<AddressClient> {
        return runBlocking {
            val res = async { createRequest(cep) }
            return@runBlocking res.await()
        }
    }

    fun findCepByNumberKtor(cep: String): AddressClient {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }

        return runBlocking {
            try {
                val response: HttpResponse = client.get(String.format("https://viacep.com.br/ws/%s/json/", cep))

                return@runBlocking response.body<AddressClient>()
            } catch (e: Exception) {
                println("Erro na solicitação GET: ${e.message}")
            } finally {
                client.close()
            }
            return@runBlocking AddressClient(
                cep = "",
                logradouro = "",
                complemento = "",
                bairro = "",
                localidade = "",
                uf = "",
                ibge = "",
                gia = "",
                ddd = "",
                siafi = ""
            )
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

        return graphQlClient.documentName("schema")
            .variable("cep", cep)
            .execute()
            .map { it.field("findCepByNumber") }
            .map { it.toEntity(AddressData::class.java) }
    }

    fun findCepByNumberFeign(cep: String): AddressClient {
        return runBlocking {
            try {
                val response: AddressClient = cepFeignClient.findCepByNumberFeign(cep)
                return@runBlocking response
            } catch (e: Exception) {
                println("Erro na solicitação GET: ${e.message}")
            }
            return@runBlocking AddressClient(
                cep = "",
                logradouro = "",
                complemento = "",
                bairro = "",
                localidade = "",
                uf = "",
                ibge = "",
                gia = "",
                ddd = "",
                siafi = ""
            )
        }
    }

    fun findCepByNumberRetrofit(cep: String): AddressClient {
        return runBlocking {
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://viacep.com.br")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
                val service: CepRetrofitClient = retrofit.create(CepRetrofitClient::class.java)
                var response: AddressClient = service.findCepByNumberRetrofit(cep).execute().body()
                    .let {
                        it?.let { it1 ->
                            AddressClient(
                                cep = it1.cep,
                                logradouro = it1.logradouro,
                                complemento = it1.complemento,
                                bairro = it1.bairro,
                                localidade = it1.localidade,
                                uf = it1.uf,
                                ibge = it1.ibge,
                                gia = it1.gia,
                                ddd = it1.ddd,
                                siafi = it1.siafi
                            )
                        }!!
                }

                return@runBlocking response
            } catch (e: Exception) {
                println("Erro na solicitação GET: ${e.message}")
            }
            return@runBlocking AddressClient(
                cep = "",
                logradouro = "",
                complemento = "",
                bairro = "",
                localidade = "",
                uf = "",
                ibge = "",
                gia = "",
                ddd = "",
                siafi = ""
            )
        }
    }

    fun findCepByNumberRX(cep: String): Observable<AddressClient> {
        return Observable.just(findCepByNumberRetrofit(cep))
    }

    @FeignClient(name = "cep", url = "https://viacep.com.br")
    interface CepFeignClient {
        @RequestMapping(method = [RequestMethod.GET], value = ["/ws/{cep}/json/"])
        fun findCepByNumberFeign(@PathVariable(name = "cep") cep: String): AddressClient
    }

    interface CepRetrofitClient {
        @GET("/ws/{cep}/json/")
        fun findCepByNumberRetrofit(@Path("cep") cep: String): Call<AddressClient>
    }

}
