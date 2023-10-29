package br.kotlin.consultacepservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun init(): WebClient{
        return WebClient.builder().build()
    }
}