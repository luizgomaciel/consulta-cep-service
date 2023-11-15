package br.kotlin.consultacepservice.studies.enums

class TesteEnumExtension {

    fun get(name: String): Any {
        return when(name) {
            TesteEnum.SEGUNDO.value -> TesteEnum.SEGUNDO
            TesteEnum.PRIMEIRO.value -> TesteEnum.PRIMEIRO
            TesteEnum.TERCEIRO.value -> TesteEnum.TERCEIRO
            else -> { "não foi encontrado o tipo selecionado" }
        }
    }

    fun get(index: Int): String {
        return when(index) {
            0 -> TesteEnum.SEGUNDO.value
            1 -> TesteEnum.PRIMEIRO.value
            2 -> TesteEnum.TERCEIRO.value
            else -> { "não foi encontrado o tipo selecionado" }
        }
    }

    fun get(type: TesteEnum): String {
        return TesteEnum.entries.first { type == it }.value
    }

}

