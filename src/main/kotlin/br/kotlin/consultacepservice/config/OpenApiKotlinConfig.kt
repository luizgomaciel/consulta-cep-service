package br.kotlin.consultacepservice.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class OpenApiKotlinConfig {

//    @Autowired
//    lateinit var buildProps: BuildProperties
//
//    @Bean
//    fun customOpenAPI(): OpenAPI{
//        val info: Info = Info()
//            .title("Documentação de API")
//            .description("Documentação de API do Cliente")
//            .version(this.buildProps.getVersion());
//
//        return OpenAPI().components(Components())
//            .info(info);
//    }
//
//    fun createTag(name: String, description: String): Tag {
//        val tag: Tag = Tag()
//        tag.name = name
//        tag.description = description
//        return tag
//    }
}

