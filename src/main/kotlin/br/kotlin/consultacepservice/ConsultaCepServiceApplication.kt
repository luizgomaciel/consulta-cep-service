package br.kotlin.consultacepservice

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

//@OpenAPIDefinition

@OpenAPIDefinition
@SpringBootApplication
@EnableFeignClients
class ConsultaCepServiceApplication

fun main(args: Array<String>) {
	runApplication<ConsultaCepServiceApplication>(*args)
}
