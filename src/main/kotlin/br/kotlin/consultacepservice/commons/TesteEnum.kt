package br.kotlin.consultacepservice.commons

enum class TesteEnum(text: String) {
    PRIMEIRO("primeiro"),
    SEGUNDO("segundo"),
    TERCEIRO("terceiro");

    val value: String = text
}