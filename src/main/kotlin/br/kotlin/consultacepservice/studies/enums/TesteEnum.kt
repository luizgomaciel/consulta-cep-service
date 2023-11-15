package br.kotlin.consultacepservice.studies.enums

enum class TesteEnum(text: String) {
    PRIMEIRO("primeiro"),
    SEGUNDO("segundo"),
    TERCEIRO("terceiro");

    val value: String = text
}