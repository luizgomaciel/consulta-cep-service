package br.kotlin.consultacepservice.studies

import br.kotlin.consultacepservice.studies.enums.TesteEnum
import br.kotlin.consultacepservice.studies.enums.TesteEnumExtension

fun main() {
    println(TesteEnumExtension().get("terceiro"))
    println(TesteEnumExtension().get(TesteEnum.SEGUNDO))
    println(TesteEnumExtension().get("seg"))
    println(TesteEnumExtension().get(4))

    val dog = Cachorro("XPTO", "Belinha", "2", "marrom, branco e preto", "Shit-zu")

    println(dog.acao(Cachorro.AcaoCachoroEnum.LATIR))
    println(dog.nome)
    println(dog.idade)
    println(dog.raca)
    println(dog.pedigre)
    println(dog.cor)

    println(dog.acao(Cachorro.AcaoCachoroEnum.BRINCAR))
    println(dog.acao(Cachorro.AcaoCachoroEnum.ANDAR))
    println(dog.acao(Cachorro.AcaoCachoroEnum.CORRER))
    println(dog.acao(Cachorro.AcaoCachoroEnum.COMER))
    println(dog.acao(Cachorro.AcaoCachoroEnum.DORMIR))
}